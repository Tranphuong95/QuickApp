export interface ICartShop {
  product?: string;
  count?: string;
}

export const defaultValue: Readonly<ICartShop> = {
  product: '',
  count: '',
};
