import dayjs from 'dayjs';

export interface IAccountDetails {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string;
  paymentItemCode?: string;
  dtDTransactionId?: string | null;
  amolDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  token?: string | null;
  transferId?: string | null;
  requestId?: number | null;
  accountName?: string;
  returnCode?: string;
  returnMessage?: string;
  externalTXNid?: string;
  transactionDate?: string;
  customerId?: string;
  customerProduct?: string;
  customerType?: string;
  accountCategory?: string;
  accountType?: string;
  accountNumber?: string;
  phoneNumber?: string;
  channel?: string;
  tranFreeField1?: string | null;
  tranFreeField2?: string | null;
  tranFreeField3?: string | null;
  tranFreeField4?: string | null;
  tranFreeField5?: string | null;
  tranFreeField6?: string | null;
  tranFreeField7?: string | null;
  tranFreeField8?: string | null;
  tranFreeField9?: string | null;
  tranFreeField10?: string | null;
  tranFreeField11?: string | null;
  tranFreeField12?: string | null;
  tranFreeField13?: string | null;
  tranFreeField14?: string | null;
  tranFreeField15?: string | null;
  tranFreeField16?: string | null;
  tranFreeField17?: string | null;
  tranFreeField18?: number | null;
  tranFreeField19?: number | null;
  tranFreeField20?: boolean | null;
  tranFreeField21?: string | null;
  tranFreeField22?: string | null;
  tranFreeField23ContentType?: string | null;
  tranFreeField23?: string | null;
  tranFreeField24?: string | null;
  tranFreeField25?: string | null;
  tranFreeField26?: string | null;
  tranFreeField27?: string | null;
  tranFreeField28?: string | null;
  tranFreeField29?: string | null;
  tranFreeField30?: string | null;
  tranFreeField31?: string | null;
  tranFreeField32?: string | null;
  tranFreeField33?: string | null;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IAccountDetails> = {
  tranFreeField20: false,
};
