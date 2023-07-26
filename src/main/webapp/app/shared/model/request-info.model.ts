import dayjs from 'dayjs';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { ErrorCodes } from 'app/shared/model/enumerations/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';

export interface IRequestInfo {
  id?: number;
  recordId?: string | null;
  transactionId?: string | null;
  requestData?: string | null;
  paramList?: string | null;
  timestamp?: string | null;
  requestRef?: string | null;
  status?: TranStatus | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: string | null;
  freeField6?: string | null;
  time?: string | null;
  errorCode?: ErrorCodes | null;
  errorMessage?: ErrorMessage | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IRequestInfo> = {};
