## 啟動說明

1. clone

   ```sh
   git clone https://github.com/WUNDSOR/exam.git
   ```

2. 用 IDE 開啟專案並啟動。

## API說明

1. 測試呼叫查詢幣別對應表資料 API，並顯示其內容。 

   ```
   /api/coin/getCoinEntity
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | type | 幣別 |

2. 測試呼叫新增幣別對應表資料 API。 

   ```
   /api/coin/addCoinEntity
   ```

   | 參數 | 說明   |
   | ---- | ------ |
   | type | 幣別   |
   | name | 中文名 |

3. 測試呼叫更新幣別對應表資料 API，並顯示其內容。 

   ```
   /api/coin/updateCoinEntity
   ```

   | 參數 | 說明   |
   | ---- | ------ |
   | type | 幣別   |
   | name | 中文名 |

4. 測試呼叫刪除幣別對應表資料 API。 

   ```
   /api/coin/removeCoinEntity
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | type | 幣別 |

5. 測試呼叫 coindesk API，並顯示其內容。 

   ```
   /api/coin/bpi
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | 無   |      |

6. 測試呼叫資料轉換的 API，並顯示其內容。

   ```
   /api/coin/bpi/detail
   ```

   | 參數 | 說明 |
   | ---- | ---- |
   | 無   |      |

