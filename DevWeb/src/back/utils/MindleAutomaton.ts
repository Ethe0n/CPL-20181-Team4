import { MindleCheckup } from "./MindleCheckup";

type Transistor = (automaton:MindleAutomaton, value:string) => Promise<TransitionResult>;
type TransitionResult = {
  'nextState': AutomatonState,
  'output'?: string[]
};
type StateResult = {
  'output': string[],
  'input': string[]
};
enum AutomatonState{
  INVALID,
  INITIAL,
  WRITE_WEATHER,
  WRITE_SKETCHBOOK,
  TAKE_TEST,
  TAKING_TEST,
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
      output: [ "start-test" ],
      input: [ "yes" ]
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
        case "yes":
          a.checkup = new MindleCheckup("test");
          return { nextState: AutomatonState.TAKING_TEST };
        case "no-later":
          return { nextState: AutomatonState.INITIAL, output: [ "first-again" ] };
      }
      return { nextState: AutomatonState.INVALID };
    },
    [AutomatonState.TAKING_TEST]: async (a, v) => {
      a.checkup = new MindleCheckup("test");

      console.log(Math.random());

      return { nextState: AutomatonState.TAKING_TEST };
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

    return this.getResult(result.output);
  }
  public getResult(output?:string[]):StateResult{
    const R = { ...MindleAutomaton.STATE_RESULTS[this.state] };

    if(output) R.output = output;
    
    return R;
  }
}