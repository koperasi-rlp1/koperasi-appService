package com.auth.koperasi.service.dao.query;

public interface QueryTransaksi {

    String QUERY_SALDO_NASABAH = "select nip, namaNasabah, sum(saldoWajib) as saldoWajib, 100000 as simpananPokok,\n" +
            "sum(saldoSukaRela) as saldoSukaRela, sum(transaksiWajib) as trWajib, \n" +
            "sum(transaksiSukaRela) as trSukaRela, sum(saldoWajib+saldoSukaRela)+100000 as saldoAkhir\n" +
            "from (\n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\tsum(tt.\"NOMINAL_TRANSAKSI\") as saldoWajib,\n" +
            "\t0 as saldoSukaRela,\n" +
            "\t0 as transaksiWajib,\n" +
            "\t0 as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN WAJIB'\n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all \n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as saldoWajib,\n" +
            "\tsum(tt.\"NOMINAL_TRANSAKSI\") as saldoSukaRela,\n" +
            "\t0 as transaksiWajib,\n" +
            "\t0 as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN SUKA RELA'\n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all \n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as saldoWajib,\n" +
            "\t0 as saldoSukaRela,\n" +
            "\tcount(*) as transaksiWajib,\n" +
            "\t0 as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN WAJIB'\n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all \n" +
            "\tselect n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as saldoWajib,\n" +
            "\t0 as saldoSukaRela,\n" +
            "\t0 as transaksiWajib,\n" +
            "\tcount(*) as transaksiSukaRela\n" +
            "\tfrom \"TN_TRANSAKSI\" tt join \"NASABAH\" n on tt.\"ID_NASABAH\" = n.\"ID_BACKUP\" \n" +
            "\twhere tt.\"JENIS_TRANSAKSI\" = 'SIMPANAN SUKA RELA'\n" +
            "\tgroup by nip, namaNasabah\n" +
            ")as datas where nip = :nip\n" +
            "group by nip, namaNasabah";

    String QUERY_PINJAMAN_NASABAH = "select nip, namaNasabah, COALESCE(sum(jumlahPinjaman), 0) as jumlahPinjaman,  COALESCE(sum(pinjamanSelesai), 0) as pinjamanSelesai, \n" +
            "COALESCE(sum(pinjamanBelumSelesai), 0) as pinjamanBelumSelesai, COALESCE(sum(totalUangPinjam), 0) as totalUangPinjam, \n" +
            "COALESCE(sum(totalUangDiBayar), 0) totalUangBayar, COALESCE(sum(totalUangPinjam-totalUangDiBayar), 0) as sisaPinjamBelumBayar \n" +
            "from (\n" +
            "\tselect \"NIP\" as nip,\n" +
            "\t\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as jumlahPinjaman,\n" +
            "\t0 as pinjamanSelesai,\n" +
            "\t0 as pinjamanBelumSelesai,\n" +
            "\t0 as totalUangPinjam,\n" +
            "\t0 as totalUangDiBayar\n" +
            "\tfrom \"NASABAH\"\n" +
            "\tunion all\n" +
            "\tselect  n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\tcount(*) as jumlahPinjaman,\n" +
            "\t0 as pinjamanSelesai,\n" +
            "\t0 as pinjamanBelumSelesai,\n" +
            "\tsum(tp.\"TOTAL_PINJAMAN\") as totalUangPinjam,\n" +
            "\tsum(tp.\"TOTAL_PINJAMAN\"-tp.\"SISA_PINJAMAN\") as totalUangDiBayar\n" +
            "\tfrom \"TN_PINJAMAN\" tp join \"NASABAH\" n on tp.\"ID_NASABAH\" = n.\"ID_BACKUP\"\n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all\n" +
            "\tselect  n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as jumlahPinjaman,\n" +
            "\tcount(*) as pinjamanSelesai,\n" +
            "\t0 as pinjamanBelumSelesai,\n" +
            "\t0 as totalUangPinjam,\n" +
            "\t0 as totalUangDiBayar\n" +
            "\tfrom \"TN_PINJAMAN\" tp join \"NASABAH\" n on tp.\"ID_NASABAH\" = n.\"ID_BACKUP\"\n" +
            "\twhere tp.\"SISA_PINJAMAN\" = 0 \n" +
            "\tgroup by nip, namaNasabah\n" +
            "\tunion all\n" +
            "\tselect  n.\"NIP\" as nip,\n" +
            "\tn.\"NAMA_NASABAH\" as namaNasabah,\n" +
            "\t0 as jumlahPinjaman,\n" +
            "\t0 as pinjamanSelesai,\n" +
            "\tcount(*) as pinjamanBelumSelesai,\n" +
            "\t0 as totalUangPinjam,\n" +
            "\t0 as totalUangDiBayar\n" +
            "\tfrom \"TN_PINJAMAN\" tp join \"NASABAH\" n on tp.\"ID_NASABAH\" = n.\"ID_BACKUP\"\n" +
            "\twhere tp.\"SISA_PINJAMAN\" > 0 \n" +
            "\tgroup by nip, namaNasabah\n" +
            ")as datas where nip = :nip\n" +
            "group by nip, namaNasabah";
}
