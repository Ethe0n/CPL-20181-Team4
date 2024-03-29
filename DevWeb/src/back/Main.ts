import Path = require("path");
import Express = require("express");
import CookieParser = require("cookie-parser");
import BodyParser = require("body-parser");
import Spdy = require("spdy");
import Logger from "jj-log";

import ReactNest = require("./utils/ReactNest");
import { getProjectData, SETTINGS } from "./utils/System";
import { Mindle } from "./utils/Route";

const SPDY_OPTIONS:Spdy.server.ServerOptions = SETTINGS['https'] ? {
  key: getProjectData(SETTINGS['https']['key']),
  cert: getProjectData(SETTINGS['https']['cert'])
} : null;
const App = Express();

App.engine("js", ReactNest.Engine);
App.set('views', Path.resolve(__dirname, "./pages"));
App.set('view engine', "js");

App.use(BodyParser.json());
App.use(CookieParser(SETTINGS['cookie-secret']));
App.use("/libs", Express.static(Path.resolve(__dirname, "./libs")));
App.use("/pages", Express.static(Path.resolve(__dirname, "./pages")));
App.use("/media", Express.static(Path.resolve(__dirname, "./media")));
App.use("/mindle", Mindle());

App.get("/gwalli/load-languages", (req, res) => {
  ReactNest.loadLanguages();
  res.sendStatus(200);
});
App.get("/", ReactNest.PageBuilder("Index"));

if(SPDY_OPTIONS){
  Spdy.createServer(SPDY_OPTIONS, App).listen(SETTINGS['port'], () => {
    Logger.success("HTTPS Server");
  });
}else{
  App.listen(SETTINGS['port'], () => {
    Logger.success("HTTP Server");
  });
}
process.on('unhandledRejection', e => {
  throw e;
});