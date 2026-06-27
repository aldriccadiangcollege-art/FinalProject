+------------------------------------------------------------+
|                        com.barangay.Ui                     |
+------------------------------------------------------------+
|  +-----------------------------------+                     |
|  |              GCashUI              |                     |
|  +-----------------------------------+                     |
|  | - scanner: Scanner                |                     |
|  | - service: TransactionService     |                     |
|  +-----------------------------------+                     |
|  | + GCashUI(scanner: Scanner)       |                     |
|  | + start(): void                   |                     |
|  | - displayHeader(): void           |                     |
|  | - registerAccount(): void         |                     |
|  | - processTransfer(): void         |                     |
|  | - viewHistory(): void             |                     |
|  +-----------------------------------+                     |
|                                                            |
|  +------------------------------------------------------+  |
|  |                  DocumentRequestUI                   |  |
|  +------------------------------------------------------+  |
|  | - scanner: Scanner                                   |  |
|  | - requestService: DocumentRequestService             |  |
|  +------------------------------------------------------+  |
|  | + DocumentRequestUI(scanner: Scanner)                |  |
|  | + launch(): void                                     |  |
|  | - createRequest(): void                              |  |
|  | - chooseDocumentType(): DocumentType                 |  |
|  | - searchByResidentName(): void                       |  |
|  | - updateRequestStatus(): void                        |  |
|  | - displayRequests(reqs: List, title: String): void   |  |
|  | - requireNonEmpty(in: String, err: String): String   |  |
|  | - parseInt(in: String, err: String): int            |  |
|  +------------------------------------------------------+  |
+------------------------------------------------------------+
       |                                       |
       | (Composes)                            | (Composes)
       v                                       v
+---------------------------------+   +---------------------------------+
|   com.barangay.services        |   |   com.barangay.services        |
|  +---------------------------+  |   |  +---------------------------+  |
|  |    TransactionService     |  |   |  |   DocumentRequestService  |  |
|  +---------------------------+  |   |  +---------------------------+  |
+---------------------------------+   +---------------------------------+
       |                                       |
       | (Uses)                                | (Uses)
       v                                       v
+-----------------------------------------------------------------------+
|                         com.barangay.models                           |
|  [Account]  [Transfer]  [AbstractDocumentRequest]                     |
|  [DocumentType (Enum)]  [RequestStatus (Enum)]                        |
|  [InvalidInputException] [PaymentProcessingException]                 |
+-----------------------------------------------------------------------+

