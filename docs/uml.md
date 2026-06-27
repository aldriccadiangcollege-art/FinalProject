+---------------------------------------------------------------------------------------------------------+
|                                              com.barangay.Ui                                            |
+---------------------------------------------------------------------------------------------------------+
|   +---------------------------------------+         +-----------------------------------------------+   |
|   |                GCashUI                |         |               DocumentRequestUI               |   |
|   +---------------------------------------+         +-----------------------------------------------+   |
|   | - scanner: Scanner                    |         | - scanner: Scanner                            |   |
|   | - service: TransactionService         |         | - requestService: DocumentRequestService      |   |
|   +---------------------------------------+         +-----------------------------------------------+   |
|   | + GCashUI(scanner: Scanner)           |         | + DocumentRequestUI(scanner: Scanner)         |   |
|   | + start(): void                       |         | + launch(): void                              |   |
|   | - displayHeader(): void               |         | - createRequest(): void                       |   |
|   | - registerAccount(): void             |         | - chooseDocumentType(): DocumentType          |   |
|   | - processTransfer(): void             |         | - searchByResidentName(): void                |   |
|   | - viewHistory(): void                 |         | - updateRequestStatus(): void                 |   |
|   +---------------------------------------+         | - displayRequests(reqs, title): void          |   |
|                       |                             | - requireNonEmpty(in, err): String            |   |
|                       | (Composes)                  | - parseInt(in, err): int                      |   |
|                       v                             +-----------------------------------------------+   |
+-----------------------|-----------------------------------------------------|---------------------------+
                        |                                                     |
========================|=====================================================|============================
                        |                                                     | (Composes)
                        v                                                     v
+---------------------------------------------------------------------------------------------------------+
|                                           com.barangay.services                                         |
+---------------------------------------------------------------------------------------------------------+
|   +------------------------------------+         +--------------------------------------------------+   |
|   |         TransactionService         |         |              DocumentRequestService              |   |
|   +------------------------------------+         +--------------------------------------------------+   |
|   | - accounts: List<Account>          |         | - requestRepository: GenericRepository           |   |
|   | - history: List<Transfer>          |         | - requestFactory: RequestFactory                 |   |
|   +------------------------------------+         | - searchEngine: RequestSearchEngine             |   |
|   | + addAccount(id, name): void       |         | - sorter: RequestSorter                          |   |
|   | + getAccounts(): List<Account>     |         | - requestCounter: int                            |   |
|   | + transfer(from, to, amt): void    |         +--------------------------------------------------+   |
|   | + getSortedHistory(): List         |         | + createRequest(resident...): AbstrDocRequest    |   |
|   +------------------------------------+         | + createRequest(id, first...): AbstrDocRequest   |   |
|                                                  | + getAllRequests(): List<AbstrDocRequest>        |   |
|   +------------------------------------+         | + getPendingRequests(): List<AbstrDocRequest>    |   |
|   |          <<interface>>             |         | + searchByResidentName(key): List                |   |
|   |          Repository<T>             |         | + sortByDate(): List<AbstrDocRequest>            |   |
|   +------------------------------------+         | + sortByResidentName(): List                     |   |
|   | + add(item: T): void               |         | + updateRequestStatus(id, status): void          |   |
|   | + getAll(): List<T>                |         | + findById(id): Optional                         |   |
|   +------------------------------------+         +--------------------------------------------------+   |
|                     ^                                       |               |              |            |
|                     | (Implements)                          | (Composes)    | (Composes)   | (Composes) |
|   +------------------------------------+                    v               v              v            |
|   |       GenericRepository<T>         |<-------------------+        +--------------+ +------------+    |
|   +------------------------------------+                             |RequestFactory| |SearchEngine|    |
|   | - items: List<T>                   |                             +--------------+ +------------+    |
|   +------------------------------------+                             |  RequestSorter              |    |
|   | + add(item: T): void               |                             +-----------------------------+    |
|   | + getAll(): List<T>                |                                                                |
|   +------------------------------------+                                                                |
+---------------------------------------------------------------------------------------------------------+
                                                                                          |
                                                                                          | (Uses / Produces)
                                                                                          v
+---------------------------------------------------------------------------------------------------------+
|                                              com.barangay.models                                        |
+---------------------------------------------------------------------------------------------------------+
|    <<interfaces>>                                                                                       |
|    +-------------------+  +-----------------+  +-------------------+  +--------------+  +-------------+ |
|    |   Identifiable    |  |    Trackable    |  |  DocumentRequest  |  |   Resident   |  |  Birthday   | |
|    +-------------------+  +-----------------+  +-------------------+  +--------------+  +-------------+ |
|    | + getId(): String |  | +getCreatedAt() |  | +getDocumentLabel|  | +getResName()|  | +getBday()  | |
|    +-------------------+  +-----------------+  +-------------------+  +--------------+  +-------------+ |
|              ^                     ^                     ^                                     ^        |
|              |                     |                     |                                     |        |
|              +---------------------+----------+----------+                                     |        |
|                                               | (Implements)                                   |        |
|                                               |                                                |        |
|                                   +-----------------------+                                    |        |
|                                   |AbstractDocumentRequest|                                    |        |
|                                   +-----------------------+                                    |        |
|                                   | - requestId: String   |                                    |        |
|                                   | - purpose: String     |                                    |        |
|                                   | - status: RequestStatus|                                   |        |
|                                   | - createdAt: LDT      |                                    |        |
|                                   +-----------------------+                                    |        |
|                                   | + getDocumentType()   |                                    |        |
|                                   +-----------------------+                                    |        |
|                                        |             |                                         |        |
|                             (Contains) |             | (Inherited By)                          |        |
|                                        v             +-------------+-------------+             |        |
|                             +--------------------+                 |             |             |        |
|                             |   ResidentRecord   |                 |             |             |        |
|                             +--------------------+                 v             v             |        |
|                             | - residentId: String|         +------------+ +------------+      |        |
|                             | - firstName: String |         |BarangayCert| |BarangayClear|      |        |
|                             | - lastName: String  |         +------------+ +------------+      |        |
|                             | - birthDate: LDate  |         |CertResidenc| |CertIndigency      |        |
|                             | - gender: String    |         +------------+ +------------+      |        |
|                             | - address: String   |                                            |        |
|                             +--------------------+                                             |        |
|                                                                                                |        |
|    <<supplementary types>>                                                                     |        |
|    +-------------------+  +--------------------+  +-------------------+                        |        |
|    |   residentName    |  | residentBirthDate  |  |   residentBDate   | -----------------------+        |
|    +-------------------+  +--------------------+  +-------------------+ (Implements)                    |
|    | - fName, lName    |  | - birthDate: LDate |  | - rBDateComponent |                        |        |
|    +-------------------+  +--------------------+  +-------------------+                        |        |
|                                                                                                |        |
|    +-------------------+  +-----------------------------------------------------------------+  |        |
|    | <<interface>>     |  | Concrete Polymorphic Implementations                            |  |        |
|    | ResidentGender    |  | [Male]       [Female]       [PreferNotToSay]                    |  |        |
|    +-------------------+  +-----------------------------------------------------------------+  |        |
|              ^                                             |                                   |        |
|              +--------------------- implements ------------+                                   |        |
|                                                                                                |        |
|    <<gcash package models>>                                                                    |        |
|    +-------------------+  +-----------------------------------------------------------------+  |        |
|    | Account <<record>>|  | Transfer <<record>>                                             |  |        |
|    +-------------------+  +-----------------------------------------------------------------+  |        |
|    | + id, name        |  | + ref: String \| + from: Account \| + to: Account \| + amt: double|  |      |
|    +-------------------+  +-----------------------------------------------------------------+  |        |
|                                                                                                |        |
|    <<enums>>              <<exceptions>>                                                       |        |
|    [DocumentType]         [InvalidInputException]                                              |        |
|    [RequestStatus]        [PaymentProcessingException]                                         |        |
+---------------------------------------------------------------------------------------------------------+