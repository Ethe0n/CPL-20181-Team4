import React = require("react");
import JJorm from "../JJorm";
import L from "../@global/Language";
import { Icon } from "../@global/components/Icon";

type State = {
  'asking'?: boolean,
  'list'?: string[]
};
export class ChatInput extends JJorm<{}, State>{
  ACTION_RECEIVER_TABLE = {};
  state:State = { asking: false };

  private $input:HTMLInputElement;

  render():React.ReactNode{
    return <div className="talk">
      <span>
        <Icon name="angle-right" />&nbsp;
      </span>
      <div className="talk-options">
        {this.state.asking && this.state.list.map(v => {
          if(v === "==="){
            return <React.Fragment key={v}>
              <input type="text" ref={this.refInput} />
              <button onClick={() => this.ontoOptionClick(`=${this.$input.value}`)}>{L.render("OK")}</button>
            </React.Fragment>;
          }else{
            return <button key={v} onClick={() => this.ontoOptionClick(v)}>
              {v[0] === "=" ? v.slice(1) : L.render(`chat-a-${v}`)}
            </button>;
          }
        })}
      </div>
    </div>;
  }
  private refInput = ($:HTMLInputElement) => this.$input = $;
  private ontoOptionClick = (key:string) => {
    JJorm.trigger('chat-in', key);
  };
}