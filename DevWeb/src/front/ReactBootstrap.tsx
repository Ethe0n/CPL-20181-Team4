import React = require("react");
import ReactDOM = require("react-dom");

import Header from "./@global/Header";
import Bayadere from "./@global/Bayadere";

export default function Bind(TargetClass:any):void{
  const $root = document.createElement("section");
  const $data = "/*{JSON.stringify($)}*/";

  $root.id = "stage";
  ReactDOM.render(<>
    {React.createElement(Header as any, $data)}
    {React.createElement(TargetClass, $data)}
    {React.createElement(Bayadere as any, $data)}
  </>, $root);
  document.body.appendChild($root);
}