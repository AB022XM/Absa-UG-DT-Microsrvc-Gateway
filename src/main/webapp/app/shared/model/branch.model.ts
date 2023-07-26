import dayjs from 'dayjs';
import { IBank } from 'app/shared/model/bank.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IBranch {
  id?: number;
  absaTranRef?: string | null;
  recordId?: string | null;
  addressId?: string | null;
  bankId?: string | null;
  branchId?: string | null;
  branchName?: string;
  branchSwiftCode?: string;
  branchPhoneNumber?: string | null;
  branchEmail?: string | null;
  branchFreeField1?: string | null;
  branchFreeField3?: string | null;
  branchFreeField4?: string | null;
  branchFreeField5?: string | null;
  branchFreeField6?: string | null;
  branchFreeField7?: string | null;
  branchFreeField8?: string | null;
  branchFreeField9?: string | null;
  branchFreeField10?: string | null;
  branchFreeField11?: string | null;
  branchFreeField12?: string | null;
  branchFreeField13?: string | null;
  branchFreeField14?: string | null;
  branchFreeField15?: string | null;
  branchFreeField16?: string | null;
  branchFreeField17?: string | null;
  branchFreeField18?: string | null;
  branchFreeField19?: string | null;
  branchFreeField20?: string | null;
  branchFreeField21?: string | null;
  branchFreeField22?: string | null;
  branchFreeField23?: string | null;
  branchFreeField24?: string | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  timestamp?: string | null;
  status?: RecordStatus | null;
  bank?: IBank | null;
}

export const defaultValue: Readonly<IBranch> = {};
