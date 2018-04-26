import React = require("react");

import JJorm from "../JJorm";
import L from "./Language";

export default class Footer extends JJorm<{ 'showLegal'?: boolean }>{
  ACTION_RECEIVER_TABLE = {};

  render():React.ReactNode{
    return <footer>
      {this.props.showLegal && <img src="/media/images/legal-information.png" />}
      <a>{L.get("footer-legal")}</a>
      <a>{L.get("footer-privacy")}</a>
      <a href="//www.ftc.go.kr/bizCommPop.do?wrkr_no=5048602028" target="_blank">{L.get("footer-business")}</a>
    </footer>;
  }
}