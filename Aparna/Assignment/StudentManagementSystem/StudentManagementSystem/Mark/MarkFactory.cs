using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace StudentManagementSystem.Mark
{
    public static class MarkFactory
    {
        public static IMark GetMarkProvider(MarkTypeEnum markType)
        {
            switch (markType)
            {
                case MarkTypeEnum.ATTENDANCE:
                    return new AttendanceMark();
                case MarkTypeEnum.INTERNAL:
                    return new InternalMark();
                case MarkTypeEnum.EXTERNAL:
                    return new ExternalMark();
                default:return null;
            }
        }
    }
}
