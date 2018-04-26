export const getLocaleText = (table:Table<string>, key:string, ...args:any[]) => {
  const R:string = table[key];

  if(!R) return `(L#${key})`;
  return R.replace(/\{#(\d+?)\}/g, (p, v1) => args[v1]);
}
export enum UserType{
  INVALID = 0b00,
  HALF_MEMBER = 0b01,
  FULL_MEMBER = 0b10,
  GROUP_MEMBER = 0b11,
  
  EMAIL_VERIFIED = 0b10000000
}