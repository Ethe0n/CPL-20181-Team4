import Express = require("express");

import ReactNest = require("./ReactNest");
import { MindleAutomaton } from "./MindleAutomaton";

export const Mindle = () => {
  const R = Express.Router();

  R.get("/", /*PreserveGuest,*/ ReactNest.PageBuilder("Mindle"));
  R.post("/", /*PreserveGuest,*/ (req, res) => {
    const automaton = MindleAutomaton.getAutomaton(req.sessionID);

    if(req.body['load']){
      res.send({
        ...automaton.getResult(),
        args: [ "test" ] //[ req.user.nickname || req.user.name ]
      } as N.ChatTransitionResult);
    }else{
      automaton.move(req.body['value']).then(result => {
        res.send({
          ...result,
          args: [ "test" ] // [ req.user.nickname || req.user.name ]
        } as N.ChatTransitionResult);
      });
    }
  });
  return R;
};