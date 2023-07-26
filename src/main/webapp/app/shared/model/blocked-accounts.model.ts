import dayjs from 'dayjs';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';

export interface IBlockedAccounts {
  id?: number;
  absaTranRef?: string | null;
  customerId?: string;
  customerAccountNumber?: string;
  dtDTransactionId?: string | null;
  blockId?: string;
  blockCode?: string;
  blockDate?: string;
  blockType?: string;
  blockStatus?: string;
  blockReason?: string;
  blockReasonCode1?: string;
  blockReasonCode2?: string;
  blockReason1?: string;
  blockReasonCode3?: string;
  blockReasonCode4?: string;
  startDate?: string;
  endDate?: string;
  isTemp?: boolean | null;
  blockFreeText?: string | null;
  blockFreeText1?: string | null;
  blockFreeText2?: string | null;
  blockFreeText3?: string | null;
  blockFreeText4?: string | null;
  blockFreeText5?: string | null;
  blockFreeText6?: string | null;
  blockReasonCode5?: string | null;
  blockReasonCode6?: string | null;
  blockReasonCode7?: string | null;
  blockReasonCode8?: string | null;
  blockReasonCode9?: string | null;
  blockReasonCode10?: string | null;
  blockReasonCode11?: string | null;
  blockReasonCode12?: string | null;
  blockReasonCode13?: string | null;
  blockReasonCode14?: string | null;
  blockReasonCode15?: string | null;
  blockReasonCode16?: string | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  delflg?: boolean | null;
  status?: RecordStatus | null;
}

export const defaultValue: Readonly<IBlockedAccounts> = {
  isTemp: false,
  delflg: false,
};
