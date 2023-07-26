import dayjs from 'dayjs';
import { ICustomer } from 'app/shared/model/customer.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { ErrorCodes } from 'app/shared/model/enumerations/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';

export interface IDTransactionSummary {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string;
  paymentItemCode?: string;
  dtDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  recordId?: string | null;
  transactionId?: string | null;
  transactionType?: string;
  transactionAmount?: string;
  transactionDate?: string | null;
  transactionStatus?: TranStatus | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: string | null;
  freeField6ContentType?: string | null;
  freeField6?: string | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  errorCode?: ErrorCodes | null;
  errorMessage?: ErrorMessage | null;
  customer?: ICustomer | null;
}

export const defaultValue: Readonly<IDTransactionSummary> = {};
