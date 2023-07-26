import dayjs from 'dayjs';
import { IApps } from 'app/shared/model/apps.model';

export interface IAppConfig {
  id?: number;
  absaTranRef?: string | null;
  recordId?: string | null;
  configId?: string | null;
  configName?: string;
  configValue?: string;
  configType?: string;
  configDescription?: string | null;
  configStatus?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: string | null;
  freeField6?: string | null;
  freeField7?: string | null;
  freeField8?: string | null;
  freeField9?: string | null;
  freeField10?: string | null;
  freeField11?: string | null;
  freeField12?: string | null;
  freeField13?: string | null;
  freeField14ContentType?: string | null;
  freeField14?: string | null;
  freeField15ContentType?: string | null;
  freeField15?: string | null;
  freeField16?: string | null;
  freeField17?: string | null;
  freeField18ContentType?: string | null;
  freeField18?: string | null;
  freeField19?: string | null;
  delflg?: boolean | null;
  timestamp?: string | null;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  apps?: IApps | null;
}

export const defaultValue: Readonly<IAppConfig> = {
  delflg: false,
};
