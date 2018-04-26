import React = require("react");

import JJorm, { ActionReceiverTable } from "../JJorm";
import Bind from "../ReactBootstrap";
import { ChatInput } from "./ChatInput";
import { ChatOutput } from "./ChatOutput";
import { XHR, XHRResponse } from "../@global/Network";
import L from "../@global/Language";

export default class Mindle extends JJorm<JJWAK.Page.Props>{
  ACTION_RECEIVER_TABLE:ActionReceiverTable = {
    'chat-in': (answer:string) => {
      XHR.post("/mindle", { value: answer }).then(this.onChatResult);
      JJorm.trigger('chat-out-me', `a-${answer}`);
    },
    'chat-req-in': (list:string[]) => {
      this.$input.setState({ asking: true, list: list });
      this.$output.scrollToBottom();
    },
    'chat-out-ai': (key:string, ...args:any[]) => {
      this.$output.talk(L.get("ai"), ChatOutput.AI_IMAGE, key, ...args);
    },
    'chat-out-me': (key:string, ...args:any[]) => {
      this.$output.talk(null, null, key, ...args);
    }
  };

  private $output:ChatOutput;
  private $input:ChatInput;

  componentDidMount():void{
    XHR.post<N.ChatTransitionResult>("/mindle", { load: true }).then(this.onChatResult);
  }
  render():React.ReactNode{
    return <article>
      <main>
        <ChatOutput ref={this.refChatOutput} user={this.props.user} />
        <ChatInput ref={this.refChatInput} />
      </main>
    </article>;
  }
  private refChatOutput = ($:ChatOutput) => this.$output = $;
  private refChatInput = ($:ChatInput) => this.$input = $;
  private onChatResult = (result:XHRResponse<N.ChatTransitionResult>) => {
    for(const v of result.result.output){
      JJorm.trigger('chat-out-ai', v, ...result.result.args);
    }
    JJorm.trigger('chat-req-in', result.result.input);
  }
}
Bind(Mindle);