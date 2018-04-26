import React = require("react");

import JJorm from "../../JJorm";
import L from "../Language";

type Props = {
  'name': string,
  'type': string,
  'value'?: string,
  'placeholder'?: boolean,
  'labelStyle'?: LabelStyle,
  'isBlock'?: boolean,
  'disabled'?: boolean
};
export enum LabelStyle{
  NONE, LEFT, RIGHT
}
export class Input extends JJorm<Props>{
  ACTION_RECEIVER_TABLE = {};

  render():React.ReactNode{
    const key = `input-${this.props.name}`;
    const $input = <input type={this.props.type} id={key} name={this.props.name}
      defaultValue={this.props.value}
      placeholder={this.props.placeholder && L.get(key)}
      disabled={this.props.disabled}
    />;
    let $R:React.ReactNode;
    
    switch(this.props.labelStyle){
      case LabelStyle.LEFT: $R = (
        <>
          <label htmlFor={key}>{L.render(key)}</label>
          {$input}
        </>
      ); break;
      case LabelStyle.RIGHT: $R = (
        <>
          {$input}
          <label htmlFor={key}>{L.render(key)}</label>
        </>
      ); break;
      case LabelStyle.NONE:
      default:
        $R = $input;
        break;
    }
    return this.props.isBlock ? <div className="input">{$R}</div> : $R;
  }
}