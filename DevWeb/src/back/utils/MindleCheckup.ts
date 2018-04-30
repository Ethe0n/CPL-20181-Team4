import { getProjectData } from "./System";
import { SmartBuffer } from "./SmartBuffer";

/*enum QuestionType{
  PICK,
  RANGE,
  TEXT
}
enum CheckupTarget{
  MALE       = 0b1000_0000,
  FEMALE     = 0b0100_0000,
  SENIOR     = 0b0000_1000,
  ADULT      = 0b0000_0100,
  ADOLESCENT = 0b0000_0010,
  KID        = 0b0000_0001
}*/
enum CommandType{
  COMMENT = 0b0000,
  NOTICE = 0b1000,
  QUESTION = 0b0001,
  PRESET_DEFINITION = 0b0010
}
enum QuestionMethod{
  DIRECT = 0b00,
  PRESET = 0b01,
  SUBJECTIVE = 0b10
}
enum PresetOverrideField{
  ANSWER_COUNTS = 0b0000_0001
}
enum CriterionFlag{
  ALWAYS = 0b1000_0000,
  OR_EQUAL = 0b0000_0010,
  GREATER_THAN = 0b0000_0001
}
type ExtensionHeader = {
  'key': string,
  'value': string
};
type Script = ScriptComment|ScriptNotice|ScriptQuestion|ScriptPresetDefinition;
type ScriptComment = {
  'command': CommandType.COMMENT,
  'data': string
};
type ScriptNotice = {
  'command': CommandType.NOTICE,
  'identifier': string,
  'data': string
};
type ScriptQuestion = {
  'command': CommandType.QUESTION,
  'identifier': string,
  'data': string
}&(ScriptQuestionDirect|ScriptQuestionPreset|ScriptQuestionSubjective);
type ScriptQuestionDirect = {
  'method': QuestionMethod.DIRECT,
  'minAnswerCount': number,
  'maxAnswerCount': number,
  'options': ScriptOption[]
};
type ScriptQuestionPreset = {
  'method': QuestionMethod.PRESET,
  'presetIdentifier': number,
  'overrideOptions': number,
  'minAnswerCount'?: number,
  'maxAnswerCount'?: number
};
type ScriptQuestionSubjective = {
  'method': QuestionMethod.SUBJECTIVE,
  'minAnswerLength': number,
  'maxAnswerLength': number
};
type ScriptPresetDefinition = {
  'command': CommandType.PRESET_DEFINITION,
  'presetIdentifier': number,
  'minAnswerCount': number,
  'maxAnswerCount': number,
  'options': ScriptOption[]
};
type ScriptOption = {
  'nextScript': string,
  'groups': AnalysisGroup[],
  'data': string
};
type AnalysisGroup = {
  'key': number,
  'value': number
};
enum AnalysisGroupType{
  INDEPENDENT = 0b00,
  DEPENDENT = 0b10
};
type Analysis = {
  'groupType': AnalysisGroupType,
  'termCount'?: number,
  'signs'?: boolean[],
  'terms'?: number[],
  'key': number,
  'name': string,
  'startValue': number,
  'criteria': AnalysisCriterion[]
};
type AnalysisCriterion = {
  'flags': number,
  'key': string,
  'value': number,
  'advice': string
};
export type StateResult = {
  'output': string[],
  'input': string[]
};
export class MindleCheckup<T = Object>{
  /*private questions:Table<MindleQuestion<T>>;
  private currentQuestion:number;
  private currentScore:T;*/
  private cursor:number;
  private presets:Table<ScriptPresetDefinition>;
  private scores:Table<number>;
  private scoreNames:Table<string>;

  private fileVersion:string;
  private code:string;
  private language:string;
  private target:number;
  private name:string;
  private extensions:ExtensionHeader[];
  private scripts:Script[];
  private analysises:Analysis[];

  public running:boolean;
  public asking:boolean;

  constructor(name:string){
    this.load(new SmartBuffer(getProjectData(`checkups/${name}.mindle`)));
  }
  private error(field:string, offset:number):Error{
    return Error(`Invalid field: ${field} at ${offset}`);
  }
  private load(buffer:SmartBuffer):void{
    if(buffer.readString(48) !== "MINDLE"){
      throw this.error("SIG", buffer.offset);
    }
    if(buffer.sum){
      throw this.error("CHK", buffer.sum);
    }
    this.fileVersion = this.loadVersion(buffer);
    this.code = buffer.readDynamicString(8);
    this.language = buffer.readString(16);
    this.target = buffer.readUnsignedInteger(8);
    this.name = buffer.readDynamicString(8);
    this.extensions = this.loadExtension(buffer, buffer.readUnsignedInteger(8));
    this.scripts = this.loadScript(buffer, buffer.readUnsignedInteger(16));
    this.analysises = this.loadAnalysis(buffer, buffer.readUnsignedInteger(16));

    debugger;
  }
  private loadVersion(buffer:SmartBuffer):string{
    const version = buffer.readUnsignedInteger(16);

    return [
      Math.floor(version / 1000),
      Math.floor(version % 1000 / 10),
      version % 10
    ].join('.');
  }
  private loadExtension(buffer:SmartBuffer, size:number):ExtensionHeader[]{
    const R:ExtensionHeader[] = [];
    
    for(let i = 0; i < size; i++){
      const item:ExtensionHeader = {
        key: buffer.readDynamicString(8),
        value: buffer.readDynamicString(16)
      };
      R.push(item);
    }
    return R;
  }
  private loadScript(buffer:SmartBuffer, size:number):Script[]{
    const R:Script[] = [];

    for(let i = 0; i < size; i++){
      const item:Partial<Script> = {
        command: buffer.readUnsignedInteger(4)
      };
      switch(item.command){
        case CommandType.COMMENT:
          item.data = buffer.readDynamicString(12);
          break;
        case CommandType.NOTICE:
          item.identifier = buffer.readDynamicString(12);
          item.data = buffer.readDynamicString(16);
          break;
        case CommandType.QUESTION:
          item.identifier = buffer.readDynamicString(12);
          item.data = buffer.readDynamicString(16);
          item.method = buffer.readUnsignedInteger(2);
          this.loadScriptQuestion(buffer, item);
          break;
        case CommandType.PRESET_DEFINITION:
          item.presetIdentifier = buffer.readUnsignedInteger(6);
          item.minAnswerCount = buffer.readUnsignedInteger(4);
          item.maxAnswerCount = buffer.readUnsignedInteger(4);
          item.options = this.loadScriptOption(buffer, buffer.readUnsignedInteger(6));
          break;
      }
      R.push(item as Script);
    }
    return R;
  }
  private loadScriptQuestion(buffer:SmartBuffer, item:Partial<ScriptQuestion>):void{
    switch(item.method){
      case QuestionMethod.DIRECT:
        item.minAnswerCount = buffer.readUnsignedInteger(4);
        item.maxAnswerCount = buffer.readUnsignedInteger(4);
        item.options = this.loadScriptOption(buffer, buffer.readUnsignedInteger(6));
        break;
      case QuestionMethod.PRESET:
        item.presetIdentifier = buffer.readUnsignedInteger(6);
        item.overrideOptions = buffer.readUnsignedInteger(8);
        if(item.overrideOptions & PresetOverrideField.ANSWER_COUNTS){
          item.minAnswerCount = buffer.readUnsignedInteger(4);
          item.maxAnswerCount = buffer.readUnsignedInteger(4);
        }
        break;
      case QuestionMethod.SUBJECTIVE:
        item.minAnswerLength = buffer.readUnsignedInteger(7);
        item.maxAnswerLength = buffer.readUnsignedInteger(7);
        break;
    }
  }
  private loadScriptOption(buffer:SmartBuffer, size:number):ScriptOption[]{
    const R:ScriptOption[] = [];

    for(let i = 0; i < size; i++){
      const item:ScriptOption = {
        nextScript: buffer.readDynamicString(12),
        groups: this.loadAnalysisGroup(buffer, buffer.readUnsignedInteger(4)),
        data: buffer.readDynamicString(8)
      };
      R.push(item);
    }
    return R;
  }
  private loadAnalysisGroup(buffer:SmartBuffer, size:number):AnalysisGroup[]{
    const R:AnalysisGroup[] = [];

    for(let i = 0; i < size; i++){
      const item:AnalysisGroup = {
        key: buffer.readUnsignedInteger(8),
        value: buffer.readSignedInteger(8)
      };
      R.push(item);
    }
    return R;
  }
  private loadAnalysis(buffer:SmartBuffer, size:number):Analysis[]{
    const R:Analysis[] = [];

    for(let i = 0; i < size; i++){
      const item:Partial<Analysis> = {
        groupType: buffer.readUnsignedInteger(2)
      };
      if(item.groupType === AnalysisGroupType.DEPENDENT){
        item.termCount = 2 + buffer.readUnsignedInteger(2);
        item.signs = [
          Boolean(buffer.readUnsignedInteger(1)),
          Boolean(buffer.readUnsignedInteger(1)),
          Boolean(buffer.readUnsignedInteger(1)),
          Boolean(buffer.readUnsignedInteger(1))
        ];
        item.terms = [];
        for(let j = 0; j < item.termCount; j++){
          item.terms.push(buffer.readUnsignedInteger(8));
        }
      }else{
        buffer.readUnsignedInteger(6);
      }
      item.key = buffer.readUnsignedInteger(8);
      item.name = buffer.readDynamicString(8);
      item.startValue = buffer.readSignedInteger(8);
      item.criteria = this.loadCriteria(buffer, buffer.readUnsignedInteger(8));

      R.push(item as Analysis);
    }
    return R;
  }
  private loadCriteria(buffer:SmartBuffer, size:number):AnalysisCriterion[]{
    const R:AnalysisCriterion[] = [];

    for(let i = 0; i < size; i++){
      const item:AnalysisCriterion = {
        flags: buffer.readUnsignedInteger(8),
        key: buffer.readDynamicString(8),
        value: buffer.readSignedInteger(8),
        advice: buffer.readDynamicString(16)
      };
      R.push(item);
    }
    return R;
  }

  public get script():Script{
    return this.scripts[this.cursor];
  }
  public get options():ScriptOption[]{
    const script:ScriptQuestion = this.script as ScriptQuestion;

    switch(script.method){
      case QuestionMethod.DIRECT:
        return script.options;
      case QuestionMethod.PRESET:
        return this.presets[script.presetIdentifier].options;
    }
    return null;
  }
  public initialize():StateResult{
    this.running = true;
    this.cursor = 0;
    this.presets = {};
    this.scores = {};
    this.scoreNames = {};

    this.analysises.forEach(v => {
      this.scores[v.key] = v.startValue;
      this.scoreNames[v.key] = v.name;
    });
    return {
      output: [ "start-test" ],
      input: [ "yes" ]
    };
  }
  public step(input:string = null):StateResult{
    if(!this.running){
      return this.initialize();
    }
    const R:StateResult = {
      output: [],
      input: []
    };
    let script:Script;

    // 직전 입력 처리
    if(this.asking){
      this.answer(input.slice(1));
      this.asking = false;
    }
    // 스크립트 읽기
    while(script = this.script){
      switch(script.command){
        case CommandType.COMMENT:
          break;
        case CommandType.NOTICE:
          R.output.push(`=${script.data}`);
          break;
        case CommandType.PRESET_DEFINITION:
          this.presets[script.presetIdentifier] = script;
          break;
        case CommandType.QUESTION:
          this.asking = true;
          return this.ask(R, script);
      }
      this.cursor++;
    }
    return null;
  }
  public answer(value:string):void{
    const options = this.options;
    let option:ScriptOption;

    if(options){
      for(const v of this.options){
        if(value === v.data){
          option = v;
          break;
        }
      }
      option.groups.forEach(v => {
        this.scores[v.key] += v.value;
      });
    }
    if(option && option.nextScript){
      this.cursor = this.scripts.findIndex(v => {
        if(v.command === CommandType.COMMENT || v.command === CommandType.PRESET_DEFINITION){
          return false;
        }
        return v.identifier === option.nextScript;
      });
    }else{
      this.cursor++;
    }
  }
  public ask(R:StateResult, script:ScriptQuestion):StateResult{
    R.output.push(`=${script.identifier}. ${script.data}`);
    switch(script.method){
      case QuestionMethod.DIRECT:
        R.input.push(...script.options.map(v => `=${v.data}`));
        break;
      case QuestionMethod.PRESET:
        R.input.push(...this.presets[script.presetIdentifier].options.map(v => `=${v.data}`));
        break;
      case QuestionMethod.SUBJECTIVE:
        R.input.push("===");
        break;
    }
    return R;
  }
  public advice():string[]{
    const R:string[] = [];

    this.analysises.forEach(v => {
      let value:number;
      let bool:boolean;

      if(v.groupType === AnalysisGroupType.INDEPENDENT){
        value = this.scores[v.key];
      }else{
        value = v.terms.reduce((pw, w, i) => {
          const coeff = i === 0
            ? 1
            : v.signs[i - 1]
            ? -1
            : 1
          ;
          return pw + coeff * this.scores[w];
        }, 0);
      }
      for(const w of v.criteria){
        if(w.flags & CriterionFlag.ALWAYS){
          R.push(`=${w.advice}`);
          break;
        }
        // 00: <
        // 01: >
        // 10: <=
        // 11: >=
        bool = w.flags & CriterionFlag.GREATER_THAN
          ? value > w.value
          : value < w.value
        ;
        if(w.flags & CriterionFlag.OR_EQUAL){
          bool = bool || value === w.value;
        }
        if(bool){
          R.push(`=${w.advice}`);
          break;
        }
      }
    });
    return R;
  }
  public toString():string{
    return Object.keys(this.scores).map(v => (
      `[${this.scoreNames[v]}]: ${this.scores[v]}`
    )).join('\n');
  }
}
/*class MindleQuestion<T>{
  private type:QuestionType;
  private typeArgs:any[];
  private content:string;
  private answers:{ 'body': string, 'reducer': (prev:T) => T }[];
}*/