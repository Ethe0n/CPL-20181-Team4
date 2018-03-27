import Path = require("path");
import Express = require("express");
import CookieParser = require("cookie-parser");
import Spdy = require("spdy");
import JJLog from "jj-log";

import ReactNest = require("./utils/ReactNest");
import { getProjectData, SETTINGS } from "./utils/System";

const SPDY_OPTIONS:Spdy.server.ServerOptions = {
  key: getProjectData("server.key"),
  cert: getProjectData("server.crt")
};
const App = Express();

App.engine("js", ReactNest.Engine);
App.set('views', Path.resolve(__dirname, "./pages"));
App.set('view engine', "js");

App.use(CookieParser(SETTINGS['cookie-secret']));
App.use("/libs", Express.static(Path.resolve(__dirname, "./libs")));
App.use("/pages", Express.static(Path.resolve(__dirname, "./pages")));

App.get("/gwalli/load-languages", (req, res) => {
  ReactNest.loadLanguages();
  res.sendStatus(200);
});
App.get("/", ReactNest.PageBuilder("Index"));

Spdy.createServer(SPDY_OPTIONS, App).listen(443, () => {
  JJLog.success("HTTPS Server");
});