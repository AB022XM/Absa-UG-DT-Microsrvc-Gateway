import dayjs from 'dayjs';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface ICurrency {
  id?: number;
  absaTranRef?: string | null;
  dtDTransactionId?: string | null;
  amolDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  token?: string | null;
  currencyUniqueId?: string | null;
  currencyCode?: string | null;
  currencyName?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: string | null;
  freeField6?: string | null;
  timestamp?: string;
  recordStatus?: RecordStatus | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<ICurrency> = {};
