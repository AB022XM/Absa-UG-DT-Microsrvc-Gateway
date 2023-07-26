import dayjs from 'dayjs';
import { IBillerAccount } from 'app/shared/model/biller-account.model';
import { IPaymentItems } from 'app/shared/model/payment-items.model';

export interface IBiller {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string;
  billerCode?: string;
  billerName?: string | null;
  billerCategoryId?: string | null;
  addressId?: string | null;
  narative?: string | null;
  narative1?: string | null;
  narative2?: string | null;
  narative3?: string | null;
  narative4?: string | null;
  narative5?: string | null;
  narative6?: string | null;
  narative7?: string | null;
  timestamp?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: string | null;
  freeField6?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
  freeField9?: string | null;
  freeField10?: string | null;
  freeField11?: string | null;
  freeField12?: string | null;
  freeField13?: string | null;
  delflg?: boolean | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  billers?: IBillerAccount[] | null;
  billers?: IPaymentItems[] | null;
  billerAccount?: IBillerAccount | null;
}

export const defaultValue: Readonly<IBiller> = {
  delflg: false,
};
