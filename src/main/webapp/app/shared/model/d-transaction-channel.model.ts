import dayjs from 'dayjs';
import { IDTransaction } from 'app/shared/model/d-transaction.model';

export interface IDTransactionChannel {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string;
  paymentItemCode?: string;
  dtDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  externalTranid?: string | null;
  channelCode?: string;
  channelName?: string;
  channelDescription?: string | null;
  timestamp?: string | null;
  chanelFreeText?: string | null;
  chanelFreeText1?: string | null;
  chanelFreeText2?: string | null;
  chanelFreeText3?: string | null;
  chanelFreeText4?: string | null;
  chanelFreeText5?: string | null;
  chanelFreeText6?: string | null;
  chanelFreeText7?: string | null;
  chanelFreeText8?: string | null;
  chanelFreeText9?: string | null;
  chanelFreeText10?: string | null;
  chanelFreeText11?: string | null;
  chanelFreeText12?: string | null;
  chanelFreeText13?: string | null;
  chanelFreeText14?: string | null;
  chanelFreeText15?: string | null;
  chanelFreeText16?: string | null;
  chanelFreeText17?: string | null;
  chanelFreeText18?: string | null;
  chanelFreeText19?: string | null;
  chanelFreeText20?: string | null;
  chanelFreeText21?: string | null;
  chanelFreeText22?: string | null;
  chanelFreeText23?: string | null;
  chanelFreeText24?: string | null;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  delflg?: boolean | null;
  dTransaction?: IDTransaction | null;
}

export const defaultValue: Readonly<IDTransactionChannel> = {
  delflg: false,
};
