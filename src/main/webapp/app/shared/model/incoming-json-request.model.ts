import dayjs from 'dayjs';

export interface IIncomingJSONRequest {
  id?: number;
  absaTranRef?: string | null;
  dtDTransactionId?: string | null;
  amolDTransactionId?: string | null;
  transactionReferenceNumber?: string | null;
  token?: string | null;
  transactionId?: string | null;
  fromEndpoint?: string | null;
  toEndpoint?: string | null;
  requestJson?: string;
  requestType?: string;
  requestDescription?: string | null;
  requestStatus?: string | null;
  additionalInfo?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  freeField5?: string | null;
  freeField6ContentType?: string | null;
  freeField6?: string | null;
  freeField7ContentType?: string | null;
  freeField7?: string | null;
  freeField8ContentType?: string | null;
  freeField8?: string | null;
  freeField9?: string | null;
  freeField10?: boolean | null;
  freeField11?: boolean | null;
  freeField12?: number | null;
  freeField13?: string | null;
  freeField14?: string | null;
  freeField15?: string | null;
  freeField16?: string | null;
  freeField17?: string | null;
  freeField18?: string | null;
  freeField19?: string | null;
  freeField20?: string | null;
  timestamp?: string;
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IIncomingJSONRequest> = {
  freeField10: false,
  freeField11: false,
};
