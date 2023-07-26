import dayjs from 'dayjs';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IDefaultSettings {
  id?: number;
  absaTranRef?: string | null;
  dtDTransactionId?: string | null;
  amolDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  token?: string | null;
  thirdPartyDTransactionId?: string | null;
  defaultSettingCode?: string | null;
  jsonSettings?: string | null;
  appConfig?: string | null;
  appName?: string | null;
  freeField?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: string | null;
  freeField6?: string | null;
  freeField8?: string | null;
  freeField9?: string | null;
  freeField10?: string | null;
  freeField11?: string | null;
  freeField12?: string | null;
  freeField13?: string | null;
  freeField14?: string | null;
  freeField15ContentType?: string | null;
  freeField15?: string | null;
  freeField16?: string | null;
  freeField17?: string | null;
  freeField18ContentType?: string | null;
  freeField18?: string | null;
  freeField19?: string | null;
  timestamp?: string | null;
  recordStatus?: RecordStatus | null;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedby?: string | null;
}

export const defaultValue: Readonly<IDefaultSettings> = {};
