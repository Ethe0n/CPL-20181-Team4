declare type Table<V> = {
  [key:string]: V
};
declare namespace JJWAK{
  namespace Page{
    interface Props{
      page:string;
      title:string;
      locale:string;
      user:JJWAK.DB.User;
      message?:string;
    }
  }
  namespace DB{
    interface User{
      id:string;
      isVerified:boolean;
      type:number;
      name:string;
      email:string;
      phone:string;
      nickname:string;
      mindCash:number;
      createdAt:Date;
      enteredAt:Date;
    }
  }
  type Settings = {
    'port': number,
    'https': {
      'key': string,
      'cert': string
    },
    'db': {
      'type': any,
      'host': string,
      'port': number,
      'username': string,
      'password': string,
      'database': string
    },
    'cookie-secret': string,
    'language-support': string[],
    'salt': string,
    'email-sender': {
      'host': string,
      'port': number,
      'auth': {
        'user': string,
        'pass': string
      }
    }
  };
}
declare namespace N{
  type ToastType = "normal"|"info"|"success"|"warn"|"error";
  type ToastImportance = "low"|"medium"|"high";
  interface Toast{
    id:number;
    type:N.ToastType,
    importance:N.ToastImportance;
    content:React.ReactNode;
  }
  interface ChatTransitionResult{
    output:string[];
    input:string[];
    args:any[];
  }
  interface Chat{
    author:{
      'title': React.ReactNode,
      'image'?: string
    };
    content:React.ReactNode;
    timestamp:Date;
    isMine?:boolean;
  }
}