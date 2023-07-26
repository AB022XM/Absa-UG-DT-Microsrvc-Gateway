import dayjs from 'dayjs';

export interface IOutgoingJSONResponse {
  id?: number;
  recordId?: string | null;
  responseId?: string | null;
  transactionId?: string | null;
  responseJson?: string;
  responseType?: string;
  responseDescription?: string | null;
  toEndpoint?: string | null;
  fromEndpoint?: string | null;
  responseStatus?: string | null;
  additionalInfo?: string | null;
  timestamp?: string;
  exceptionMessage?: string | null;
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
  createdAt?: string | null;
  createdBy?: string | null;
  updatedAt?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IOutgoingJSONResponse> = {};
