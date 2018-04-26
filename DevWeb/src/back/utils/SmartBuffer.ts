export class SmartBuffer{
  private buffer:Buffer;
  public bitOffset:number;

  constructor(buffer:Buffer){
    this.buffer = buffer;
    this.bitOffset = 0;
  }
  private read(bitLength:number):number{
    const range:[number, number] = [ this.bitOffset, this.bitOffset + bitLength ];
    let R:number = 0;

    for(let i = range[0]; i < range[1]; i++){
      R <<= 1;
      R |= this.buffer[i >> 3] & (1 << (7 - i % 8)) ? 1 : 0;
    }
    this.move(bitLength);
    return R;
  }
  private move(bitOffset:number):number{
    return this.bitOffset += bitOffset;
  }
  public get offset():number{
    return this.bitOffset >> 3;
  }
  public get sum():number{
    return this.buffer.reduce((pv, v) => {
      return (pv + v) % 0xFF;
    }, 0);
  }
  public readSignedInteger(bitLength:number):number{
    let value = this.readUnsignedInteger(bitLength);

    if(value & (1 << bitLength - 1)){
      value = -(~value + 1);
    }
    return value;
  }
  public readUnsignedInteger(bitLength:number):number{
    return this.read(bitLength);
  }
  public readString(bitLength:number):string{
    const byteLength:number = bitLength >> 3;
    const R:number[] = [];

    for(let i = 0; i < byteLength; i++){
      R.push(this.readUnsignedInteger(8));
    }
    return Buffer.from(R).toString();
  }
  public readDynamicString(bitLengthLength:number):string{
    const bitLength = 8 * this.readUnsignedInteger(bitLengthLength);
    
    return this.readString(bitLength);
  }
}