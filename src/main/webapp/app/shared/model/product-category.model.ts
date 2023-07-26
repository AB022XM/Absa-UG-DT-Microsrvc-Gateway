import dayjs from 'dayjs';

export interface IProductCategory {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string | null;
  recordId?: string | null;
  productCategoryCode?: string;
  productCategoryName?: string;
  productCategoryDescription?: string | null;
  productCategoryImage?: string | null;
  productFreeField1?: string | null;
  productFreeField2?: string | null;
  productFreeField3?: string | null;
  productFreeField4?: string | null;
  productFreeField5?: string | null;
  productFreeField6?: string | null;
  productFreeField7?: string | null;
  productFreeField8?: string | null;
  productFreeField9?: string | null;
  productFreeField10?: string | null;
  productFreeField11?: string | null;
  delflg?: boolean | null;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IProductCategory> = {
  delflg: false,
};
