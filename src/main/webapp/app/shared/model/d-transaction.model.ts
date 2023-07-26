import dayjs from 'dayjs';
import { IDTransactionHistory } from 'app/shared/model/d-transaction-history.model';
import { IDTransactionSummary } from 'app/shared/model/d-transaction-summary.model';
import { IDTransactionDetails } from 'app/shared/model/d-transaction-details.model';
import { IAmolDTransactions } from 'app/shared/model/amol-d-transactions.model';
import { IDTransactionChannel } from 'app/shared/model/d-transaction-channel.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { Channel } from 'app/shared/model/enumerations/channel.model';
import { ErrorCodes } from 'app/shared/model/enumerations/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';

export interface IDTransaction {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string;
  paymentItemCode?: string;
  dtDTransactionId?: string | null;
  amolDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  externalTranid?: string | null;
  token?: string | null;
  transferId?: string | null;
  productCode?: string;
  paymentChannelCode?: Channel;
  accountNumber?: string;
  amount?: number;
  debitAmount?: number;
  creditAmount?: number | null;
  settlementAmount?: number | null;
  settlementDate?: string | null;
  transactionDate?: string | null;
  isRetried?: boolean | null;
  lastRetryDate?: string | null;
  firstRetryDate?: string | null;
  retryResposeCode?: ErrorCodes | null;
  retryResponseMessage?: ErrorMessage | null;
  retryCount?: number | null;
  isRetriableFlg?: boolean | null;
  doNotRetryDTransaction?: boolean | null;
  status?: TranStatus | null;
  statusCode?: string | null;
  statusDetails?: string | null;
  retries?: number | null;
  timestamp?: string | null;
  postedBy?: string | null;
  postedDate?: string | null;
  internalErrorCode?: string | null;
  externalErrorCode?: string | null;
  isCrossCurrency?: boolean | null;
  isCrossBank?: boolean | null;
  currency?: string;
  creditAccount?: string | null;
  debitAccount?: string | null;
  phoneNumber?: string | null;
  customerNumber?: string | null;
  tranStatus?: TranStatus | null;
  tranStatusDetails?: string | null;
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
  tranFreeField13?: number | null;
  tranFreeField14ContentType?: string | null;
  tranFreeField14?: string | null;
  tranFreeField15?: string | null;
  tranFreeField16?: string | null;
  tranFreeField17?: boolean | null;
  tranFreeField18?: string | null;
  tranFreeField19?: string | null;
  tranFreeField20?: string | null;
  tranFreeField21?: string | null;
  tranFreeField22?: boolean | null;
  tranFreeField23?: string | null;
  tranFreeField24?: string | null;
  tranFreeField25?: string | null;
  tranFreeField26?: string | null;
  tranFreeField27?: string | null;
  tranFreeField28?: string | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  transaction?: IDTransactionHistory | null;
  transaction?: IDTransactionSummary | null;
  transaction?: IDTransactionDetails | null;
  transactions?: IAmolDTransactions[] | null;
  transactions?: IDTransactionChannel[] | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IDTransaction> = {
  isRetried: false,
  isRetriableFlg: false,
  doNotRetryDTransaction: false,
  isCrossCurrency: false,
  isCrossBank: false,
  tranFreeField17: false,
  tranFreeField22: false,
};
