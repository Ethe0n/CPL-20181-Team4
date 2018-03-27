import FS = require("fs");
import Path = require("path");

/**
 * 프로젝트 데이터 폴더의 데이터를 동기식으로 읽어 그 내용을 반환한다.
 * 
 * @param path 프로젝트 데이터 폴더에서의 하위 경로
 */
export function getProjectData(path:string):Buffer{
  return FS.readFileSync(Path.resolve(__dirname, `../data/${path}`));
}
export const SETTINGS:DDS.Settings = JSON.parse(getProjectData("settings.json").toString());