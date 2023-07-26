import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAmolDTransactions } from 'app/shared/model/amol-d-transactions.model';
import { searchEntities, getEntities } from './amol-d-transactions.reducer';

export const AmolDTransactions = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const amolDTransactionsList = useAppSelector(state => state.absaugdtmicrosrvcgateway.amolDTransactions.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.amolDTransactions.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const startSearching = e => {
    if (search) {
      dispatch(searchEntities({ query: search }));
    }
    e.preventDefault();
  };

  const clear = () => {
    setSearch('');
    dispatch(getEntities({}));
  };

  const handleSearch = event => setSearch(event.target.value);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="amol-d-transactions-heading" data-cy="AmolDTransactionsHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.home.title">Amol D Transactions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/amol-d-transactions/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.home.createLabel">
              Create new Amol D Transactions
            </Translate>
          </Link>
        </div>
      </h2>
      <Row>
        <Col sm="12">
          <Form onSubmit={startSearching}>
            <FormGroup>
              <InputGroup>
                <Input
                  type="text"
                  name="search"
                  defaultValue={search}
                  onChange={handleSearch}
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.home.search')}
                />
                <Button className="input-group-addon">
                  <FontAwesomeIcon icon="search" />
                </Button>
                <Button type="reset" className="input-group-addon" onClick={clear}>
                  <FontAwesomeIcon icon="trash" />
                </Button>
              </InputGroup>
            </FormGroup>
          </Form>
        </Col>
      </Row>
      <div className="table-responsive">
        {amolDTransactionsList && amolDTransactionsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.amolDTransactionId">Amol D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferId">Transfer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.externalTxnId">External Txn Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.customerReference">Customer Reference</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitAccountNumber">Debit Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditAccountNumber">
                    Credit Account Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitAmount">Debit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditAmount">Credit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transactionAmount">Transaction Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitDate">Debit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditDate">Credit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.settlementDate">Settlement Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitCurrency">Debit Currency</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerName">Payer Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerAddress">Payer Address</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerEmail">Payer Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerPhoneNumber">Payer Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerNarration">Payer Narration</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBranchId">Payer Branch Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryName">Beneficiary Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryAccount">Beneficiary Account</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryBranchId">
                    Beneficiary Branch Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryPhoneNumber">
                    Beneficiary Phone Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranStatus">Tran Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration1">Narration 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration2">Narration 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration3">Narration 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxAmount">Tax Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxGLAccount">Tax GL Account</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxNarration">Tax Narration</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.internalErrorCode">Internal Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.externalErrorCode">External Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeBranchId">Payee Branch Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductInstanceReference">
                    Payee Product Instance Reference
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductCode">Payee Product Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductName">Payee Product Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductDescription">
                    Payee Product Description
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductUnitOfMeasure">
                    Payee Product Unit Of Measure
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductQuantity">
                    Payee Product Quantity
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.subCategoryCode">Sub Category Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCode">Payer Bank Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankName">Payer Bank Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankAddress">Payer Bank Address</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCity">Payer Bank City</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankState">Payer Bank State</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCountry">Payer Bank Country</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankPostalCode">
                    Payer Bank Postal Code
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.checkerId">Checker Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.remittanceInformation">
                    Remittance Information
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.paymentChannelCode">Payment Channel Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeAmount">Fee Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeGLAccount">Fee GL Account</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeNarration">Fee Narration</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField1">Tran Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField2">Tran Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField3">Tran Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField4">Tran Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField5">Tran Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField6">Tran Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField7">Tran Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField8">Tran Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField9">Tran Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField10">Tran Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField11">Tran Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField12">Tran Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField13">Tran Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField14">Tran Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField15">Tran Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField16">Tran Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField17">Tran Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField18">Tran Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField19">Tran Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField20">Tran Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField21">Tran Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField22">Tran Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField23">Tran Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField24">Tran Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseCode">Response Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseMessage">Response Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseDetails">Response Details</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferReferenceId">
                    Transfer Reference Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferStatus">Transfer Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseDateTime">Response Date Time</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.dTransaction">D Transaction</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {amolDTransactionsList.map((amolDTransactions, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/amol-d-transactions/${amolDTransactions.id}`} color="link" size="sm">
                      {amolDTransactions.id}
                    </Button>
                  </td>
                  <td>{amolDTransactions.absaTranRef}</td>
                  <td>{amolDTransactions.billerId}</td>
                  <td>{amolDTransactions.dtDTransactionId}</td>
                  <td>{amolDTransactions.amolDTransactionId}</td>
                  <td>{amolDTransactions.transactionReferenceNumber}</td>
                  <td>{amolDTransactions.token}</td>
                  <td>{amolDTransactions.transferId}</td>
                  <td>{amolDTransactions.externalTxnId}</td>
                  <td>{amolDTransactions.customerReference}</td>
                  <td>{amolDTransactions.debitAccountNumber}</td>
                  <td>{amolDTransactions.creditAccountNumber}</td>
                  <td>{amolDTransactions.debitAmount}</td>
                  <td>{amolDTransactions.creditAmount}</td>
                  <td>{amolDTransactions.transactionAmount}</td>
                  <td>
                    {amolDTransactions.debitDate ? (
                      <TextFormat type="date" value={amolDTransactions.debitDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {amolDTransactions.creditDate ? (
                      <TextFormat type="date" value={amolDTransactions.creditDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${amolDTransactions.status}`} />
                  </td>
                  <td>
                    {amolDTransactions.settlementDate ? (
                      <TextFormat type="date" value={amolDTransactions.settlementDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.Currency.${amolDTransactions.debitCurrency}`} />
                  </td>
                  <td>
                    {amolDTransactions.timestamp ? (
                      <TextFormat type="date" value={amolDTransactions.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{amolDTransactions.phoneNumber}</td>
                  <td>{amolDTransactions.email}</td>
                  <td>{amolDTransactions.payerName}</td>
                  <td>{amolDTransactions.payerAddress}</td>
                  <td>{amolDTransactions.payerEmail}</td>
                  <td>{amolDTransactions.payerPhoneNumber}</td>
                  <td>{amolDTransactions.payerNarration}</td>
                  <td>{amolDTransactions.payerBranchId}</td>
                  <td>{amolDTransactions.beneficiaryName}</td>
                  <td>{amolDTransactions.beneficiaryAccount}</td>
                  <td>{amolDTransactions.beneficiaryBranchId}</td>
                  <td>{amolDTransactions.beneficiaryPhoneNumber}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${amolDTransactions.tranStatus}`} />
                  </td>
                  <td>{amolDTransactions.narration1}</td>
                  <td>{amolDTransactions.narration2}</td>
                  <td>{amolDTransactions.narration3}</td>
                  <td>{amolDTransactions.taxAmount}</td>
                  <td>{amolDTransactions.taxGLAccount}</td>
                  <td>{amolDTransactions.taxNarration}</td>
                  <td>{amolDTransactions.internalErrorCode}</td>
                  <td>{amolDTransactions.externalErrorCode}</td>
                  <td>{amolDTransactions.payeeBranchId}</td>
                  <td>{amolDTransactions.payeeProductInstanceReference}</td>
                  <td>{amolDTransactions.payeeProductCode}</td>
                  <td>{amolDTransactions.payeeProductName}</td>
                  <td>{amolDTransactions.payeeProductDescription}</td>
                  <td>{amolDTransactions.payeeProductUnitOfMeasure}</td>
                  <td>{amolDTransactions.payeeProductQuantity}</td>
                  <td>{amolDTransactions.subCategoryCode}</td>
                  <td>{amolDTransactions.payerBankCode}</td>
                  <td>{amolDTransactions.payerBankName}</td>
                  <td>{amolDTransactions.payerBankAddress}</td>
                  <td>{amolDTransactions.payerBankCity}</td>
                  <td>{amolDTransactions.payerBankState}</td>
                  <td>{amolDTransactions.payerBankCountry}</td>
                  <td>{amolDTransactions.payerBankPostalCode}</td>
                  <td>{amolDTransactions.checkerId}</td>
                  <td>{amolDTransactions.remittanceInformation}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.Channel.${amolDTransactions.paymentChannelCode}`} />
                  </td>
                  <td>{amolDTransactions.feeAmount}</td>
                  <td>{amolDTransactions.feeGLAccount}</td>
                  <td>{amolDTransactions.feeNarration}</td>
                  <td>{amolDTransactions.tranFreeField1}</td>
                  <td>{amolDTransactions.tranFreeField2}</td>
                  <td>{amolDTransactions.tranFreeField3}</td>
                  <td>{amolDTransactions.tranFreeField4}</td>
                  <td>{amolDTransactions.tranFreeField5}</td>
                  <td>{amolDTransactions.tranFreeField6}</td>
                  <td>{amolDTransactions.tranFreeField7}</td>
                  <td>{amolDTransactions.tranFreeField8}</td>
                  <td>{amolDTransactions.tranFreeField9}</td>
                  <td>{amolDTransactions.tranFreeField10}</td>
                  <td>{amolDTransactions.tranFreeField11}</td>
                  <td>{amolDTransactions.tranFreeField12}</td>
                  <td>{amolDTransactions.tranFreeField13}</td>
                  <td>{amolDTransactions.tranFreeField14}</td>
                  <td>{amolDTransactions.tranFreeField15}</td>
                  <td>{amolDTransactions.tranFreeField16}</td>
                  <td>{amolDTransactions.tranFreeField17}</td>
                  <td>{amolDTransactions.tranFreeField18}</td>
                  <td>{amolDTransactions.tranFreeField19}</td>
                  <td>{amolDTransactions.tranFreeField20 ? 'true' : 'false'}</td>
                  <td>{amolDTransactions.tranFreeField21}</td>
                  <td>{amolDTransactions.tranFreeField22}</td>
                  <td>
                    {amolDTransactions.tranFreeField23 ? (
                      <div>
                        {amolDTransactions.tranFreeField23ContentType ? (
                          <a onClick={openFile(amolDTransactions.tranFreeField23ContentType, amolDTransactions.tranFreeField23)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {amolDTransactions.tranFreeField23ContentType}, {byteSize(amolDTransactions.tranFreeField23)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {amolDTransactions.tranFreeField24 ? (
                      <TextFormat type="date" value={amolDTransactions.tranFreeField24} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{amolDTransactions.responseCode}</td>
                  <td>{amolDTransactions.responseMessage}</td>
                  <td>{amolDTransactions.responseDetails}</td>
                  <td>{amolDTransactions.transferReferenceId}</td>
                  <td>{amolDTransactions.transferStatus}</td>
                  <td>
                    {amolDTransactions.responseDateTime ? (
                      <TextFormat type="date" value={amolDTransactions.responseDateTime} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {amolDTransactions.createdAt ? (
                      <TextFormat type="date" value={amolDTransactions.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{amolDTransactions.createdBy}</td>
                  <td>
                    {amolDTransactions.updatedAt ? (
                      <TextFormat type="date" value={amolDTransactions.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{amolDTransactions.updatedBy}</td>
                  <td>
                    {amolDTransactions.dTransaction ? (
                      <Link to={`/d-transaction/${amolDTransactions.dTransaction.id}`}>{amolDTransactions.dTransaction.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/amol-d-transactions/${amolDTransactions.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/amol-d-transactions/${amolDTransactions.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/amol-d-transactions/${amolDTransactions.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.home.notFound">No Amol D Transactions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AmolDTransactions;
