import dayjs from 'dayjs';
import { IBiller } from 'app/shared/model/biller.model';

export interface IPaymentItems {
  id?: number;
  absaTranRef?: string | null;
  recordId?: string | null;
  productCategoryId?: number | null;
  billerId?: number | null;
  paymentItemCode?: string | null;
  paymentItemId?: number | null;
  paymentItemName?: string | null;
  paymentItemDescription?: string | null;
  isActive?: boolean | null;
  hasFixedPrice?: boolean | null;
  hasVariablePrice?: boolean | null;
  hasDiscount?: boolean | null;
  hasTax?: boolean | null;
  amount?: number | null;
  chargeAmount?: number | null;
  hasChargeAmount?: boolean | null;
  taxAmount?: number | null;
  freeText?: string | null;
  freeText1?: string | null;
  freeText2?: string | null;
  freeText3?: string | null;
  freeText4?: string | null;
  freeText5?: string | null;
  freeText6?: string | null;
  freeText7?: string | null;
  freeText8?: string | null;
  freeText9?: string | null;
  freeText10?: string | null;
  freeText11?: string | null;
  freeText12?: string | null;
  freeText13?: string | null;
  freeText14?: string | null;
  freeText15?: string | null;
  freeText16?: string | null;
  freeText17?: string | null;
  freeText18?: string | null;
  freeText19?: string | null;
  delflg?: boolean | null;
  status?: string | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  deletedAt?: string | null;
  deletedBy?: string | null;
  biller?: IBiller | null;
}

export const defaultValue: Readonly<IPaymentItems> = {
  isActive: false,
  hasFixedPrice: false,
  hasVariablePrice: false,
  hasDiscount: false,
  hasTax: false,
  hasChargeAmount: false,
  delflg: false,
};
