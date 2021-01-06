export interface IDevice {
  id?: number;
  tensanpham?: string;
  kichthuocmatthung?: string;
  kichthuocthanthung?: string;
  phukien?: string;
  chatlieu?: string;
  baohanh?: string;
  diachi?: string;
  hotline?: string;
}

export const defaultValue: Readonly<IDevice> = {};
