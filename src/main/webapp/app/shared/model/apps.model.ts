import dayjs from 'dayjs';
import { IAppConfig } from 'app/shared/model/app-config.model';

export interface IApps {
  id?: number;
  recordId?: string | null;
  configId?: string | null;
  appId?: string;
  appCurrentVersion?: string;
  appName?: string;
  appDescription?: string | null;
  appStatus?: string | null;
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
  delflg?: boolean | null;
  timestamp?: string | null;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedby?: string | null;
  apps?: IAppConfig[] | null;
}

export const defaultValue: Readonly<IApps> = {
  delflg: false,
};
