import React = require("react");

import { Icon } from "./components/Icon";
import { getLocaleText } from "../../model";

type PatternResolver = (key:number, ...args:string[]) => React.ReactNode;

export default class L{
  private static readonly TABLE = (window as any)['__LANGUAGE'];
  private static readonly PATTERN_RESOLVER:Table<PatternResolver> = {
    'B': (key, text) => <b key={key}>{text}</b>,
    'BR': key => <br key={key} />,
    'FA': (key, name) => <Icon key={key} name={name} />,
    'SPAN': (key, text, className) => <span key={key} className={className}>{text}</span>,
    'REF': (key, name, ...args) => <React.Fragment key={key}>{L.render(name, ...args)}</React.Fragment>
  };

  public static get(key:string, ...args:any[]):string{
    return getLocaleText(L.TABLE, key, ...args);
  }
  public static render(key:string, ...args:any[]):React.ReactNode{
    const R:React.ReactNode[] = [];
    const PATTERN:RegExp = /\{\{(\w+?)(?:\|(.+?))?\}\}/g;
    let value:string;
    let execArray:RegExpExecArray;
    let prevIndex:number = 0;
    let isCapsulated:boolean = key[0] === '.';
    
    if(isCapsulated) key = key.slice(1);
    if(!L.TABLE[key]) return `(L#${key})`;
    
    value = L.get(key, ...args);
    while(execArray = PATTERN.exec(value)){
      if(execArray.index - prevIndex > 0){
        R.push(value.slice(prevIndex, execArray.index));
      }
      R.push(L.PATTERN_RESOLVER[execArray[1]](R.length, ...(execArray[2] ? execArray[2].split('|') : [])));
      prevIndex = PATTERN.lastIndex;
    }
    if(prevIndex < value.length){
      R.push(value.slice(prevIndex));
    }
    return isCapsulated ? <span className={`lang lang-${key}`}>{R}</span> : R;
  }
}
delete (window as any)['__LANGUAGE'];