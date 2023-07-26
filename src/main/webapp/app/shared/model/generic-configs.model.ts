import dayjs from 'dayjs';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IGenericConfigs {
  id?: number;
  absaTranRef?: string | null;
  recordId?: string | null;
  configId?: string | null;
  configName?: string | null;
  configsDetails?: string | null;
  additionalConfigs?: string | null;
  freeField?: boolean | null;
  freeField1?: boolean | null;
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
  freeField20?: string | null;
  freeField21?: string | null;
  freeField22?: string | null;
  freeField23?: string | null;
  freeField24?: string | null;
  freeField25ContentType?: string | null;
  freeField25?: string | null;
  freeField26?: string | null;
  freeField27?: string | null;
  freeField28ContentType?: string | null;
  freeField28?: string | null;
  freeField29?: string | null;
  freeField30?: string | null;
  freeField31?: string | null;
  freeField32?: string | null;
  freeField33?: string | null;
  freeField34?: string | null;
  timestamp?: string;
  recordStatus?: RecordStatus | null;
}

export const defaultValue: Readonly<IGenericConfigs> = {
  freeField: false,
  freeField1: false,
};
