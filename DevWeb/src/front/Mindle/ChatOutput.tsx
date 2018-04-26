import React = require("react");
import JJorm from "../JJorm";
import L from "../@global/Language";

type Props = {
  'user': JJWAK.DB.User
};
type State = {
  'log': N.Chat[]
};
export class ChatOutput extends JJorm<Props, State>{
  public static AI_IMAGE:string = "/media/images/icon-32.png";
  private static LOG_LIMIT:number = 100;

  ACTION_RECEIVER_TABLE = {};
  state:State = { log: [] };

  private $chatBoard:HTMLDivElement;

  render():React.ReactNode{
    return <div ref={this.refChatBoard} className="chat-board">
      {this.state.log.map((v, i) => {
        const isPreamble = this.state.log[i + 1] &&
          this.checkSameAuthor(this.state.log[i + 1], v)
        ;
        const isTiny = this.state.log[i - 1] &&
          this.checkSameAuthor(this.state.log[i - 1], v)
        ;
        return <Chat key={i} data={v} isPreamble={isPreamble} isTiny={isTiny} />;
      })}
    </div>;
  }
  private refChatBoard = ($:HTMLDivElement) => this.$chatBoard = $;
  private checkSameAuthor(a:N.Chat, b:N.Chat):boolean{
    return JSON.stringify(a.author) === JSON.stringify(b.author);
  }
  public scrollToBottom():void{
    this.$chatBoard.scrollTop = this.$chatBoard.scrollHeight;
  }
  public talk(title:React.ReactNode, image:string, key:string, ...args:any[]):void{
    if(this.state.log.push({
      author: title ? {
        title: title,
        image: image
      } : null,
      content: L.render(`chat-${key}`, ...args),
      timestamp: new Date(),
      isMine: title === null
    }) >= ChatOutput.LOG_LIMIT){
      this.state.log.shift();
    }
    this.forceUpdate();
  }
}
const Chat = (props:{ 'data': N.Chat, 'isPreamble'?: boolean, 'isTiny'?: boolean }) => {
  const classList:string[] = [ "chat" ];

  if(props.data.isMine) classList.push("chat-mine");
  if(props.isPreamble) classList.push("chat-preamble");
  if(props.isTiny) classList.push("chat-tiny");

  return <div className={classList.join(' ')}>
    {!props.data.isMine && <div className="chat-author">
      <img src={props.data.author.image} />
      <label>{props.data.author.title}</label>
    </div>}
    <div className="body">
      <div className="chat-content">{props.data.content}</div>
      <div className="chat-timestamp">
        {props.data.timestamp.toLocaleTimeString()}
      </div>
    </div>
  </div>
};