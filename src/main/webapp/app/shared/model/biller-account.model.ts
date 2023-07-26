import dayjs from 'dayjs';
import { IBiller } from 'app/shared/model/biller.model';

export interface IBillerAccount {
  id?: number;
  absaTranRef?: string | null;
  recordId?: string;
  billerId?: string;
  billerName?: string;
  billerAccNumber?: string;
  billerAccountDescription?: string | null;
  timestamp?: string | null;
  billerFreeField1?: string | null;
  billerFreeField2?: string | null;
  billerFreeField3?: string | null;
  billerFreeField4?: string | null;
  billerFreeField5?: string | null;
  billerFreeField6?: string | null;
  billerFreeField7?: string | null;
  billerFreeField8?: string | null;
  billerFreeField9?: string | null;
  billerFreeField10?: string | null;
  billerFreeField11?: string | null;
  billerFreeField12?: string | null;
  billerFreeField13?: string | null;
  delflg?: boolean | null;
  createdAt?: string;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
  billerAccounts?: IBiller[] | null;
  biller?: IBiller | null;
}

export const defaultValue: Readonly<IBillerAccount> = {
  delflg: false,
};
