import { MindleCheckup, StateResult } from "./MindleCheckup";
import Logger from "jj-log";

type Transistor = (automaton:MindleAutomaton, value:string) => Promise<TransitionResult>;
type TransitionResult = {
  'nextState': AutomatonState,
  'outputResolver'?: (arr:string[]) => string[],
  'inputResolver'?: (arr:string[]) => string[]
};
enum AutomatonState{
  INVALID,
  INITIAL,
  WRITE_WEATHER,
  WRITE_SKETCHBOOK,
  TAKE_TEST,
  TAKING_TEST,
  TAKEN_TEST,
  RESULT_TEST,
  READ_REPORT
}
export class MindleAutomaton{
  private static STATE_RESULTS:Table<StateResult> = {
    [AutomatonState.INVALID]: {
      output: [ "oops-0", "oops-1" ],
      input: [ "restart" ]
    },
    [AutomatonState.INITIAL]: {
      output: [ "welcome", "first" ],
      input: [ "write-weather", "write-sketchbook", "take-test", "read-report" ]
    },
    [AutomatonState.TAKE_TEST]: {
      output: [ "lets-test", "ready-test" ],
      input: [ "yes", "no-later" ]
    },
    [AutomatonState.TAKING_TEST]: {
      output: [ "restart-test" ],
      input: [ "yes", "no-later" ]
    },
    [AutomatonState.TAKEN_TEST]: {
      output: [ "finish-test-0", "finish-test-1" ],
      input: [ "yes" ]
    },
    [AutomatonState.RESULT_TEST]: {
      output: [],
      input: [ "yes-so" ]
    }
  };
  private static table:Table<MindleAutomaton> = {};
  private static transitions:Table<Transistor> = {
    [AutomatonState.INVALID]: async (a, v) => {
      return { nextState: AutomatonState.INITIAL };
    },
    [AutomatonState.INITIAL]: async (a, v) => {
      switch(v){
        case "take-test": return { nextState: AutomatonState.TAKE_TEST };
      }
      return { nextState: AutomatonState.INVALID };
    },
    [AutomatonState.TAKE_TEST]: async (a, v) => {
      switch(v){
        case "yes":{
          let result:StateResult;

          a.checkup = new MindleCheckup("test");
          result = a.checkup.step();

          return {
            nextState: AutomatonState.TAKING_TEST,
            outputResolver: () => result.output,
            inputResolver: () => result.input
          };
        }
        case "no-later":
          return { nextState: AutomatonState.INITIAL, outputResolver: () => [ "first-again" ] };
      }
      return { nextState: AutomatonState.INVALID };
    },
    [AutomatonState.TAKING_TEST]: async (a, v) => {
      if(v === "no-later"){
        return { nextState: AutomatonState.INITIAL, outputResolver: () => [ "first-again" ] };
      }
      const result = a.checkup.step(v);

      if(!result){
        return {
          nextState: AutomatonState.TAKEN_TEST,
          outputResolver: () => [ "finish-test-0", "finish-test-1" ]
        };
      }
      Logger.log(
        "MindleAutomaton %F_CYAN%CHECKUP%NORMAL%",
        a.checkup.toString(),
        `Next: ${(a.checkup.script as any)['identifier']} - ${(a.checkup.script as any)['data']}`
      );
      return {
        nextState: AutomatonState.TAKING_TEST,
        outputResolver: () => result.output,
        inputResolver: () => result.input
      };
    },
    [AutomatonState.TAKEN_TEST]: async (a, v) => {
      const advices = a.checkup.advice();

      Logger.info(
        "MindleAutomaton",
        a.checkup.toString(),
        `Advices: ${advices.join(' ')}`
      );
      return {
        nextState: AutomatonState.RESULT_TEST,
        outputResolver: () => advices
      };
    },
    [AutomatonState.RESULT_TEST]: async (a, v) => {
      return { nextState: AutomatonState.INITIAL, outputResolver: () => [ "first-again" ] };
    }
  };

  public static getAutomaton(sessionId:string):MindleAutomaton{
    return MindleAutomaton.table[sessionId]
      || (MindleAutomaton.table[sessionId] = new MindleAutomaton())
    ;
  }

  private checkup:MindleCheckup;
  private state:AutomatonState;

  constructor(){
    this.state = AutomatonState.INITIAL;
  }
  public async move(value:string):Promise<StateResult>{
    const transistor = MindleAutomaton.transitions[this.state];
    let result:TransitionResult;

    if(!transistor) return MindleAutomaton.STATE_RESULTS[AutomatonState.INVALID];

    result = await transistor(this, value);
    this.state = result.nextState;

    Logger.log("MindleAutomaton %F_YELLOW%NEXT%NORMAL%", AutomatonState[this.state]);

    return this.getResult(result);
  }
  public getResult(result:TransitionResult = null):StateResult{
    const R = { ...MindleAutomaton.STATE_RESULTS[this.state] };

    if(result){
      if(result.outputResolver) R.output = result.outputResolver(R.output);
      if(result.inputResolver) R.input = result.inputResolver(R.input);
    }
    return R;
  }
}