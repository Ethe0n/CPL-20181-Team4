import React = require("react");
import JJorm from "../../JJorm";
import L from "../Language";

type Props = {
  'progressText'?: React.ReactNode,
  'onClick': (e:React.MouseEvent<HTMLElement>) => void
};
type State = {
  'step': AsyncStep
};
enum AsyncStep{
  INVALID, READY, PROGRESS
}
export class AsyncButton extends JJorm<Props, State>{
  ACTION_RECEIVER_TABLE = {};
  state = { step: AsyncStep.READY };

  render():React.ReactNode{
    return <button className="abutton"
      disabled={this.state.step !== AsyncStep.READY}
      onClick={this.onClick}
    >
      {this.state.step === AsyncStep.PROGRESS
        ? this.props.progressText || L.render("PROGRESS")
        : this.props.children
      }
    </button>;
  }
  private onClick = (e:React.MouseEvent<HTMLElement>) => {
    this.setState({ step: AsyncStep.PROGRESS });
    this.props.onClick(e);
  };
  public done():void{
    this.setState({ step: AsyncStep.READY });
  }
}