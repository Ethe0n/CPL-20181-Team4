import React = require("react");

import { ToastManager } from "./components/Toast";
import JJorm from "../JJorm";
import L from "./Language";

export default class Bayadere extends JJorm<JJWAK.Page.Props>{
  ACTION_RECEIVER_TABLE = {};

  componentDidMount():void{
    if(this.props.message){
      JJorm.trigger('toast', L.render(`message-${this.props.message}`));
    }
  }
  render():React.ReactNode{
    return <aside className="bayadere">
      <ToastManager />
    </aside>;
  }
}