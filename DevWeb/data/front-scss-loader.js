const FS = require("fs");
const SASS = require("node-sass");
const JJLog = require("jj-log").default;
const PAGE = process.argv[2];
const IS_FOR_PROC = process.argv[3] === "!";

const OPTIONS = {
  file: `./src/front/${PAGE}/style.scss`,
  outFile: `./dist/pages/${PAGE}.css`
};
if(IS_FOR_PROC){
  JJLog.warn("PRODUCTION MODE!");
  OPTIONS.outputStyle = "compressed";
  SASS.render(OPTIONS, onComplete);
}else{
  SASS.render(OPTIONS, onComplete);
  FS.watchFile(OPTIONS.file, { interval: 1000 }, (c, p) => {
    JJLog.info(`%F_CYAN%WATCH%NORMAL% SCSS ${PAGE}`);
    SASS.render(OPTIONS, onComplete);
  });
}
function onComplete(err, res){
  if(err){
    JJLog.error(err);
    return;
  }
  FS.writeFile(OPTIONS.outFile, res.css, err => {
    if(err){
      JJLog.error(err);
      return;
    }
    JJLog.success(`${PAGE} at ${res.stats.duration}ms`);
  });
}