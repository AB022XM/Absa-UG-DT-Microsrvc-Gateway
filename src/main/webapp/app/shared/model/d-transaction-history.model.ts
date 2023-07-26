import dayjs from 'dayjs';
import { ICustomer } from 'app/shared/model/customer.model';
import { Channel } from 'app/shared/model/enumerations/channel.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';
import { ModeOfPayment } from 'app/shared/model/enumerations/mode-of-payment.model';

export interface IDTransactionHistory {
  id?: number;
  recordId?: string | null;
  transferId?: string | null;
  productCode?: string;
  paymentChannelCode?: Channel;
  debitAccountNumber?: string;
  creditAccountNumber?: string;
  debitAmount?: number | null;
  creditAmount?: number | null;
  transactionAmount?: number | null;
  debitDate?: string;
  creditDate?: string;
  status?: TranStatus | null;
  settlementDate?: string | null;
  debitCurrency?: Currency | null;
  timestamp?: string;
  phoneNumber?: string;
  email?: string;
  payerName?: string | null;
  payerAddress?: string | null;
  payerEmail?: string | null;
  payerPhoneNumber?: string | null;
  payerNarration?: string | null;
  payerBranchId?: string | null;
  beneficiaryName?: string | null;
  beneficiaryAccount?: string | null;
  beneficiaryBranchId?: string | null;
  beneficiaryPhoneNumber?: string | null;
  tranStatus?: TranStatus | null;
  narration1?: string | null;
  narration2?: string | null;
  narration3?: string | null;
  modeOfPayment?: ModeOfPayment | null;
  retries?: number | null;
  posted?: boolean | null;
  apiId?: string | null;
  apiVersion?: string | null;
  postingApi?: string | null;
  isPosted?: boolean | null;
  postedBy?: string | null;
  postedDate?: string | null;
  internalErrorCode?: string | null;
  externalErrorCode?: string | null;
  tranFreeField1?: string | null;
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
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IDTransactionHistory> = {
  posted: false,
  isPosted: false,
};
