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

  render():React.ReactNode{
    return <div className="talk">
      <span>
        <Icon name="angle-right" />&nbsp;
      </span>
      <div className="talk-options">
        {this.state.asking && this.state.list.map(v => (
          <button key={v} onClick={() => this.ontoOptionClick(v)}>
            {L.render(`chat-a-${v}`)}
          </button>
        ))}
      </div>
    </div>;
  }
  private ontoOptionClick = (key:string) => {
    JJorm.trigger('chat-in', key);
  };
}