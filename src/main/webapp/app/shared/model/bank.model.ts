import dayjs from 'dayjs';
import { IBranch } from 'app/shared/model/branch.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IBank {
  id?: number;
  absaTranRef?: string | null;
  billerId?: string;
  paymentItemCode?: string;
  dtDTransactionId?: string | null;
  amolDTransactionId?: string | null;
  bankName?: string;
  bankSwiftCode?: string;
  bankBranchId?: string | null;
  bankPhoneNumber?: string | null;
  bankEmail?: string | null;
  bankFreeField1?: string | null;
  bankFreeField3?: string | null;
  bankFreeField4?: string | null;
  bankFreeField5?: string | null;
  bankFreeField6?: string | null;
  bankFreeField7?: string | null;
  bankFreeField8?: string | null;
  bankFreeField9?: string | null;
  bankFreeField10?: string | null;
  bankFreeField11?: string | null;
  bankFreeField12?: string | null;
  bankFreeField13?: string | null;
  bankFreeField14?: string | null;
  bankFreeField15?: string | null;
  bankFreeField16?: string | null;
  bankFreeField17?: string | null;
  bankFreeField18?: string | null;
  bankFreeField19?: string | null;
  bankFreeField20?: string | null;
  bankFreeField21?: string | null;
  bankFreeField22?: string | null;
  bankFreeField23?: string | null;
  bankFreeField24?: string | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  delflg?: boolean | null;
  status?: RecordStatus | null;
  banks?: IBranch[] | null;
}

export const defaultValue: Readonly<IBank> = {
  delflg: false,
};
