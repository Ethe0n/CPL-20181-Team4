declare type Table<V> = {
  [key:string]: V
};
declare namespace DDS{
  namespace Page{
    type Name = "Index";
    interface Props{
      'page': DDS.Page.Name
    }
  }
  type Settings = {
    'cookie-secret': string,
    'language-support': string[]
  };
}