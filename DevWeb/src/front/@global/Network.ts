import JJorm from "../JJorm";
import L from "./Language";

interface XHROptions{
  method:"GET"|"POST",
  url:string,
  data?:string,
  headers?:Table<string>
}
export interface XHRResponse<T>{
  status:number,
  result:T
}
type XHRResultHandler = (status:number, result:any) => void;

export const move = (url:string) => {
  location.href = url;
};
export class XHR{
  private static requests:Table<XHR> = {};
  private static requestId:number = 0;

  private static send<T>(options:XHROptions):Promise<XHRResponse<T>>{
    return new Promise((res, rej) => {
      const xhr = new XHR(options, (status, result) => {
        const R = {
          status: status,
          result: result
        };
        let content:React.ReactNode;

        delete XHR.requests[xhr.id];
        if(!result || status >= 400){
          if(result && result['content']){
            content = L.render(`error-${status}-${result['content']}`);
          }else{
            content = L.get("toast-xhr-error", options.url, status);
          }
          JJorm.trigger('toast', content, "error", "medium");
          rej(R);
        }else{
          res(R);
        }
      });
    });
  }
  public static get<T>(url:string):Promise<XHRResponse<T>>{
    return XHR.send<T>({
      method: "GET",
      url: url
    });
  }
  public static post<T>(url:string, data?:FormData|Table<any>):Promise<XHRResponse<T>>{
    let jsonData:Table<any> = data;

    if(data instanceof FormData){
      jsonData = {};
      for(const v of data.entries()){
        jsonData[v[0]] = v[1];
      }
    }
    return XHR.send<T>({
      method: "POST",
      url: url,
      data: JSON.stringify(jsonData),
      headers: {
        'Content-Type': "application/json;charset=utf-8"
      }
    });
  }

  private id:number;
  private source:XMLHttpRequest;

  constructor(options:XHROptions, res?:XHRResultHandler){
    this.id = ++XHR.requestId;
    this.source = new XMLHttpRequest();
    this.source.open(options.method, options.url, true);
    for(const i in options.headers){
      this.source.setRequestHeader(i, options.headers[i]);
    }
    this.source.onreadystatechange = this.getOnReadyStateChangeClosure(res);
    this.source.send(options.data);

    XHR.requests[this.id] = this;
  }
  private getOnReadyStateChangeClosure(res:XHRResultHandler):() => void{
    return () => {
      if(this.source.readyState !== XMLHttpRequest.DONE) return;
      let data:string|Table<any> = this.source.response;

      if(this.source.getResponseHeader('Content-Type').startsWith("application/json")){
        data = JSON.parse(String(data));
      }
      res(this.source.status, data);
    };
  }
}