import dayjs from 'dayjs';
import { ModeOfPayment } from 'app/shared/model/enumerations/mode-of-payment.model';

export interface IPaymentConfig {
  id?: number;
  recordId?: string | null;
  paymentId?: string | null;
  paymentName?: string;
  paymentType?: string;
  paymentDescription?: string | null;
  paymentStatus?: string | null;
  paymentMethod?: ModeOfPayment | null;
  paymentAmount?: number | null;
  additionalConfig?: string | null;
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
  freeField20?: string | null;
  timestamp?: string;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IPaymentConfig> = {};
