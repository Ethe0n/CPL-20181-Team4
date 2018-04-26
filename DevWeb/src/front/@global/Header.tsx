import React = require("react");
import Cookies = require("js-cookie");

import JJorm from "../JJorm";
import L from "./Language";

export default class Header extends JJorm<JJWAK.Page.Props>{
  ACTION_RECEIVER_TABLE = {};

  render():React.ReactNode{
    // flex-flow: row-reverse이기 때문에 메뉴가 역순으로 출력된다.
    return <header>
      <a href="/">
        <img src="/media/images/logo.png" />
      </a>
      <nav>
        <Submenu title={L.render("nav-language")} tiny={true}>
          <a href="#" onClick={() => this.changeLanguage("ko-KR")}>한국어</a>
          <a href="#" onClick={() => this.changeLanguage("en-US")}>English</a>
        </Submenu>
        <Account {...this.props.user} />
        <a href="/store">{L.get("nav-store")}</a>
        <Submenu title={L.get("nav-introduction")}>
          <a href="/introduction">{L.get("nav-about-mindelevation")}</a>
          <a href="/about/sketchbook">{L.get("nav-about-sketchbook")}</a>
        </Submenu>
      </nav>
    </header>;
  }
  private changeLanguage(locale:string):void{
    Cookies.set('locale', locale);
    location.reload();
  }
}
class Submenu extends JJorm<{ 'title': React.ReactNode, 'tiny'?: boolean }, { 'opened': boolean }>{
  ACTION_RECEIVER_TABLE = {};
  state = { opened: false };

  render():React.ReactNode{
    return <div className="submenu"
      style={this.props.tiny ? { flexBasis: 0 } : {}}
      onMouseEnter={this.onMouseEnter}
      onMouseLeave={this.onMouseLeave}
    >
      {this.props.title}
      {this.state.opened && <span>{this.props.children}</span>}
    </div>;
  }
  private onMouseEnter = (e:React.MouseEvent<HTMLElement>) => {
    this.setState({ opened: true });
  };
  private onMouseLeave = (e:React.MouseEvent<HTMLElement>) => {
    this.setState({ opened: false });
  };
}
const Account = (user:JJWAK.DB.User) => user.id
  ? <Submenu title={L.get("nav-account")}>
    <a href="/account">{L.render("nav-account-on", user.id)}</a>
    <a href="/account/logout">{L.get("nav-account-logout")}</a>
  </Submenu>
  : <a href="/account/login">{L.get("nav-account")}</a>
;