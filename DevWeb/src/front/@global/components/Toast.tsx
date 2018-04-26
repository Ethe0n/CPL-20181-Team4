import React = require("react");

import JJorm from "../../JJorm";
import { Icon } from "./Icon";

type ToastManagerState = {
  'table': Table<N.Toast>
};
type ToastProps = {
  'manager': ToastManager,
  'toast': N.Toast
};
type ToastState = {
  'height'?: number,
  'disappearing'?: boolean
};

export class ToastManager extends JJorm<{}, ToastManagerState>{
  private static toastId:number = 0;

  ACTION_RECEIVER_TABLE = {
    'toast': (content:React.ReactNode, type:N.ToastType, importance:N.ToastImportance) => {
      const id = ++ToastManager.toastId;

      this.state.table[id] = {
        id: id,
        type: type,
        importance: importance,
        content: content
      };
      this.forceUpdate();
    }
  };
  state:ToastManagerState = { table: {} };

  render():React.ReactNode{
    return <div className="toast-list">
      {Object.keys(this.state.table).map(k => (
        <Toast key={k} manager={this} toast={this.state.table[k]} />
      ))}
    </div>;
  }
  public onToastFinish(toastId:number):void{
    delete this.state.table[toastId];
    this.forceUpdate();
  }
}
export class Toast extends JJorm<ToastProps, ToastState>{
  private static LIFE_SHORT:number = 3000;
  private static LIFE_LONG:number = 10000;

  ACTION_RECEIVER_TABLE = {};
  state:ToastState = { disappearing: false };

  private $div:HTMLDivElement;
  private lifeTimer:number;

  componentDidMount():void{
    switch(this.props.toast.importance){
      case "high":
        break;
      case "medium":
        this.lifeTimer = Number(setTimeout(this.disappear, Toast.LIFE_LONG));
        break;
      case "low":
      default:
        this.lifeTimer = Number(setTimeout(this.disappear, Toast.LIFE_SHORT));
        break;
    }
  }
  componentWillUnmount():void{
    if(this.lifeTimer){
      clearTimeout(this.lifeTimer);
      this.lifeTimer = undefined;
    }
  }
  render():React.ReactNode{
    const classList:string[] = [ "toast", `toast-${this.props.toast.type}` ];
    let $headIcon:React.ReactNode;

    switch(this.props.toast.type){
      case "info": $headIcon = <Icon name="info-circle" />; break;
      case "success": $headIcon = <Icon name="check-circle" />; break;
      case "warn": $headIcon = <Icon name="exclamation-triangle" />; break;
      case "error": $headIcon = <Icon name="exclamation-circle" />; break;
    }
    if(this.state.disappearing) classList.push("toast-disappear");

    return <div className={classList.join(' ')}
      ref={this.refDiv}
      {...(this.state.disappearing ? {
        style: { marginBottom: -this.state.height },
        onAnimationEnd: this.onDisappearFinish
      } : {})}
    >
      {$headIcon}
      <div className="content">
        {this.props.toast.content}
      </div>
      <button className="tiny" onClick={this.disappear}>
        <Icon name="times" />
      </button>
    </div>;
  }
  private refDiv = ($:HTMLDivElement) => this.$div = $;
  private disappear = () => {
    this.setState({
      height: this.$div.offsetHeight,
      disappearing: true
    });
  };
  private onDisappearFinish = () => {
    this.props.manager.onToastFinish(this.props.toast.id);
  };
}