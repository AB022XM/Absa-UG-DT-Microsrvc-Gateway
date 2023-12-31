import dayjs from 'dayjs';
import { ICustomer } from 'app/shared/model/customer.model';
import { Channel } from 'app/shared/model/enumerations/channel.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { ModeOfPayment } from 'app/shared/model/enumerations/mode-of-payment.model';

export interface IDTransactionDetails {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string;
  paymentItemCode?: string;
  dtDTransactionId?: string | null;
  amolDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  token?: string | null;
  transferId?: string | null;
  productCode?: string;
  paymentChannelCode?: Channel;
  debitAccountNumber?: string;
  creditAccountNumber?: string;
  debitAmount?: number;
  creditAmount?: number | null;
  transactionAmount?: number | null;
  debitDate?: string;
  creditDate?: string;
  status?: TranStatus | null;
  settlementDate?: string | null;
  debitCurrency?: string;
  timestamp?: string | null;
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
  narration4?: string | null;
  narration5?: string | null;
  narration6?: string | null;
  narration7?: string | null;
  narration8?: string | null;
  narration9?: string | null;
  narration10?: string | null;
  narration11?: string | null;
  narration12?: string | null;
  modeOfPayment?: ModeOfPayment | null;
  retries?: number | null;
  posted?: boolean | null;
  apiId?: string | null;
  apiVersion?: string | null;
  postingApi?: string | null;
  isPosted?: boolean | null;
  postedBy?: string | null;
  postedDate?: string | null;
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
  tranFreeField12ContentType?: string | null;
  tranFreeField12?: string | null;
  tranFreeField13?: string | null;
  tranFreeField14?: string | null;
  tranFreeField15ContentType?: string | null;
  tranFreeField15?: string | null;
  tranFreeField16?: string | null;
  tranFreeField17?: string | null;
  tranFreeField18?: string | null;
  tranFreeField19?: string | null;
  tranFreeField20?: string | null;
  tranFreeField21?: string | null;
  tranFreeField22?: string | null;
  tranFreeField23?: string | null;
  tranFreeField24?: string | null;
  tranFreeField25?: string | null;
  tranFreeField26?: string | null;
  tranFreeField27?: string | null;
  tranFreeField28?: string | null;
  internalErrorCode?: string | null;
  externalErrorCode?: string | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IDTransactionDetails> = {
  posted: false,
  isPosted: false,
};
