import Crypto = require("crypto");
import FS = require("fs");
import Path = require("path");
import Logger from "jj-log";

const PBKDF2_ITER = 121234;
const PBKDF2_LENGTH = 64;
const PBKDF2_METHOD = "sha512";

/**
 * 프로젝트 데이터 폴더의 데이터를 동기식으로 읽어 그 내용을 반환한다.
 * 
 * @param path 프로젝트 데이터 폴더에서의 하위 경로
 */
export function getProjectData(path:string):Buffer{
  try{
    return FS.readFileSync(Path.resolve(__dirname, `../data/${path}`));
  }catch(e){
    Logger.error(e);
    return null;
  }
}
/**
 * 주어진 문자열을 단방향 암호화하여 반환한다.
 * 
 * @param text 암호화할 문자열
 */
export function getEncrypted(text:string):string{
  return Crypto.pbkdf2Sync(text, SETTINGS['salt'], PBKDF2_ITER, PBKDF2_LENGTH, PBKDF2_METHOD).toString("hex");
}
export const SETTINGS:JJWAK.Settings = JSON.parse(getProjectData("settings.json").toString());