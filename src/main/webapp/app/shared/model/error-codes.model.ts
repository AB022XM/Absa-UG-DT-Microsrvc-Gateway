import dayjs from 'dayjs';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';

export interface IErrorCodes {
  id?: number;
  absaTranRef?: string | null;
  recordId?: string | null;
  paymentItemCode?: string;
  dtDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  externalTranid?: string | null;
  errorCode?: string;
  errorMessage?: ErrorMessage | null;
  responseCode?: string | null;
  responseMessage?: string | null;
  responseBody?: string | null;
  timestamp?: string | null;
  requestRef?: string | null;
  status?: TranStatus | null;
  customerField1?: string | null;
  customerField2?: string | null;
  customerField3?: string | null;
  customerField4?: string | null;
  customerField5?: string | null;
  customerField6?: string | null;
  customerField7?: string | null;
  customerField8?: string | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IErrorCodes> = {};
